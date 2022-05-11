package ru.itis.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    Optional<T> findById(Integer id);
    List<T> findAll();
    void save(T item);
    void delete(Integer id);
}
