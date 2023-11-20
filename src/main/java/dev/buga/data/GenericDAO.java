package dev.buga.data;

import org.hibernate.SessionFactory;

import java.util.List;

public interface GenericDAO<T> {
    void create(T t);
    T read(Long id);
    List<T> readAll();
    void update(T t);
    void delete(Long id);
    SessionFactory requestSessionFactory();
}
