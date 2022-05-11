package ru.itis.services;

import ru.itis.dto.AddBookForm;
import ru.itis.dto.UserDto;
import ru.itis.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void addBook(AddBookForm addBookForm);
    UserDto getUserByEmail (String email);
    List<Book> getBySellerId(Integer sellerId);
    Optional<Book> findBookById (Integer bookId);
    List<Book> findAllBook();
}
