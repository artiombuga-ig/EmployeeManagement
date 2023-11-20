package dev.buga.data;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import org.hibernate.query.Query;
import java.util.List;

public class GenericDAOImpl<T> implements GenericDAO<T> {
    private final Class<T> type;
    private final LocalSessionFactoryBean sessionFactoryBean;
    private final SessionFactory sessionFactory;

    public GenericDAOImpl(Class<T> type, LocalSessionFactoryBean sessionFactoryBean) {
        this.type = type;
        this.sessionFactoryBean = sessionFactoryBean;
        this.sessionFactory = getSessionFactory();
    }

    private SessionFactory getSessionFactory() {
        return sessionFactoryBean.getObject();
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
    public List<T> readAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<T> resultList = null;
            try {
                String queryString = "FROM " + type.getName();
                Query<T> query = session.createQuery(queryString, type);
                resultList = query.getResultList();

                transaction.commit();
            } catch (Exception e) {
                handleException(e, transaction);
            }
            return resultList;
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
                transaction.commit();
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

    public SessionFactory requestSessionFactory() {
        return this.sessionFactory;
    }

}
