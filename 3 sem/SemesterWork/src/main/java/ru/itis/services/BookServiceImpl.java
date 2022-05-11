package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.AddBookForm;
import ru.itis.dto.UserDto;
import ru.itis.models.Book;
import ru.itis.repositories.BooksRepository;
import ru.itis.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BooksRepository booksRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public BookServiceImpl(BooksRepository booksRepository, UsersRepository usersRepository){
        this.booksRepository = booksRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public void addBook(AddBookForm form) {
        Book book = Book.builder()
                .name(form.getName())
                .author(form.getAuthor())
                .year(form.getYear())
                .description(form.getDescription())
                .price(form.getPrice())
                .sellerId(form.getSellerId())
                .imageId(form.getImageId())
                .build();

        booksRepository.save(book);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return UserDto.from(usersRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<Book> getBySellerId(Integer sellerId) {
        return booksRepository.findBySellerId(sellerId);
    }

    @Override
    public Optional<Book> findBookById(Integer bookId) {
        return booksRepository.findById(bookId);
    }

    @Override
    public List<Book> findAllBook() {
        return booksRepository.findAll();
    }
}
