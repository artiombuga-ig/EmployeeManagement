package dev.buga.data;

public interface GenericDAO<T> {
    void create(T t);
    T read(Long id);
    void update(T t);
    void delete(Long id);
}
