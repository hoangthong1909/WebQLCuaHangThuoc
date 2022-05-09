package Dao;

import java.util.List;

public interface DaoInterface<T> {
    T findById(Integer id);
    List<T> findAll();
    T create(T entity);
    T update(T entity);
    T delete (T entity);
}
