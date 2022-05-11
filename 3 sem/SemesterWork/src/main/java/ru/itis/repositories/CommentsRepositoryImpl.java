package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.models.Book;
import ru.itis.models.Comments;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentsRepositoryImpl implements CommentsRepository {

    private final static String SQL_INSERT = "insert into comments(user_id, book_id, text, created_at) " +
            "values (?, ?, ?, ?)";

    private final static String SQL_SELECT_BY_ID = "select c.id as comments_id, text, created_at,\n" +
            "       user_id, first_name, last_name, age, city, password, email, avatar_id,\n" +
            "       book_id, name, author, year, description, price, seller_id, image_id\n" +
            "from comments c\n" +
            "    left join users u on c.user_id = u.id\n" +
            "    left join books b on c.book_id = b.id where c.id = ?";

    private final static String SQL_SELECT_ALL = "select c.id as comments_id, text, created_at,\n" +
            "       user_id, first_name, last_name, age, city, password, email, avatar_id,\n" +
            "       book_id, name, author, year, description, price, seller_id, image_id\n" +
            "from comments c\n" +
            "    left join users u on c.user_id = u.id\n" +
            "    left join books b on c.book_id = b.id order by created_at desc";

    private final static String SQL_SELECT_BY_BOOK_ID = "select c.id as comments_id, text, created_at,\n" +
            "       user_id, first_name, last_name, age, city, password, email, avatar_id,\n" +
            "       book_id, name, author, year, description, price, seller_id, image_id\n" +
            "from comments c\n" +
            "    left join users u on c.user_id = u.id\n" +
            "    left join books b on c.book_id = b.id where book_id = ?";

    private final RowMapper<Comments> rowMapper = (row, rowNumber) -> Comments.builder()
            .id(row.getInt("comments_id"))
            .user(
                    User.builder()
                            .id(row.getInt("user_id"))
                            .firstName(row.getString("first_name"))
                            .lastName(row.getString("last_name"))
                            .age(row.getInt("age"))
                            .city(row.getString("city"))
                            .email(row.getString("email"))
                            .password(row.getString("password"))
                            .avatarId(row.getInt("avatar_id"))
                            .build()
            )
            .book(
                    Book.builder()
                            .id(row.getInt("book_id"))
                            .name(row.getString("name"))
                            .author(row.getString("author"))
                            .year(row.getInt("year"))
                            .description(row.getString("description"))
                            .price(row.getInt("price"))
                            .sellerId(row.getInt("seller_id"))
                            .imageId(row.getInt("image_id"))
                            .build()
            )
            .text(row.getString("text"))
            .createdAt(row.getTimestamp("created_at"))
            .build();

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentsRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Comments> findById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Comments> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    @Override
    public List<Comments> findByBookId(Integer bookId) {
        return jdbcTemplate.query(SQL_SELECT_BY_BOOK_ID, rowMapper, bookId);
    }

    @Override
    public void save(Comments item) {
        if(item.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setInt(1, item.getUser().getId());
                statement.setInt(2, item.getBook().getId());
                statement.setString(3, item.getText());
                statement.setTimestamp(4, item.getCreatedAt());
                return statement;
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                item.setId(keyHolder.getKey().intValue());
            }
        }
    }

    @Override
    public void delete(Integer id) { }
}
