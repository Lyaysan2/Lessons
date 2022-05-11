package ru.itis.repositories;

import ru.itis.models.Book;

import java.util.List;

public interface BooksRepository extends CrudRepository<Book> {
    List<Book> findBySellerId(Integer id);
}
