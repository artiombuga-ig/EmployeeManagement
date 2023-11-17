package dev.buga.data;

import dev.buga.utilities.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GenericDAOImpl<T> implements GenericDAO<T> {
    private final Class<T> type;
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public GenericDAOImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public void create(T t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            session.persist(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public T read(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        T t = null;
        try {
            t = session.get(type, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void update(T t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            session.merge(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            T t = session.get(type, id);
            if (t != null) {
                session.remove(t);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
