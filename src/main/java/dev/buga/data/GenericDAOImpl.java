package dev.buga.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope("prototype")
@Transactional
public class GenericDAOImpl<T> implements GenericDAO<T> {
    private final Class<T> type;
    @Getter
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public GenericDAOImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public void create(T t) {
        entityManager.persist(t);
    }

    @Override
    public T read(Long id) {
        return entityManager.find(type, id);
    }

    @Override
    public List<T> readAll() {
        String queryString = "FROM " + type.getName();
        return entityManager.createQuery(queryString, type).getResultList();
    }

    @Override
    public void update(T t) {
        entityManager.merge(t);
    }

    @Override
    public void delete(Long id) {
        T t = entityManager.find(type, id);
        if (t != null) {
            entityManager.remove(t);
        }
    }

}
