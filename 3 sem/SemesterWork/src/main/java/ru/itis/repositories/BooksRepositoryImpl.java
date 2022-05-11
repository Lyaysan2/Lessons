package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.models.Book;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class BooksRepositoryImpl implements BooksRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from books where id = ?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from books";
    //language=SQL
    private static final String SQL_INSERT = "insert into books (name, author, year, description, price, seller_id, image_id) values (?, ?, ?, ?, ?, ?, ?)";
    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from books where id = ?";
    //language=SQL
    private static final String SQL_SELECT_BY_SELLER_ID = "select * from books where seller_id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Book> bookRowMapper = (row, rowNumber) -> Book.builder()
            .id(row.getInt("id"))
            .name(row.getString("name"))
            .author(row.getString("author"))
            .year(row.getInt("year"))
            .description(row.getString("description"))
            .price(row.getInt("price"))
            .sellerId(row.getInt("seller_id"))
            .imageId(row.getInt("image_id"))
            .build();

    @Override
    public Optional<Book> findById(Integer id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, bookRowMapper, id));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, bookRowMapper);
    }

    @Override
    public void save(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[] {"id"});

            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getYear());
            statement.setString(4, book.getDescription());
            statement.setInt(5, book.getPrice());
            statement.setInt(6, book.getSellerId());
            statement.setInt(7, book.getImageId());

            return statement;
        }, keyHolder);
        book.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public List<Book> findBySellerId(Integer id) {
        return jdbcTemplate.query(SQL_SELECT_BY_SELLER_ID, bookRowMapper, id);
    }
}
