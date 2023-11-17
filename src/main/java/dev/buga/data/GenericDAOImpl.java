package dev.buga.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GenericDAOImpl<T> implements GenericDAO<T> {
    private final Class<T> type;
    private final SessionFactory sessionFactory;

    public GenericDAOImpl(Class<T> type, SessionFactory sessionFactory) {
        this.type = type;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(T t) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(t);
                transaction.commit();
            } catch (Exception e) {
                handleException(e, transaction);
            }
        }
    }

    @Override
    public T read(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            T t = null;
            try {
                t = session.get(type, id);
                transaction.commit();
            } catch (Exception e) {
                handleException(e, transaction);
            }
            return t;
        }
    }

    @Override
    public void update(T t) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(t);
                transaction.commit();
            } catch (Exception e) {
                handleException(e, transaction);
            }
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                T t = session.get(type, id);
                if (t != null) {
                    session.remove(t);
                }
            } catch (Exception e) {
                handleException(e, transaction);
            }
        }
    }

    private static void handleException(Exception e, Transaction transaction) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        e.printStackTrace();
    }

}
