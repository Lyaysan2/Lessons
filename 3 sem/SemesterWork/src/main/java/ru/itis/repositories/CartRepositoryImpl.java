package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.models.Book;
import ru.itis.models.Cart;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class CartRepositoryImpl implements CartRepository {

    //language=SQL
    private final static String SQL_SELECT_BY_ID = "select c.id, user_id, first_name, last_name, age, city, password, email, avatar_id,\n" +
            "       book_id, name, author, year, description, price, seller_id, image_id\n" +
            "from cart c\n" +
            "    left join users u on c.user_id = u.id\n" +
            "    left join books b on c.book_id = b.id where c.id = ?\n";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select c.id, user_id, first_name, last_name, age, city, password, email, avatar_id,\n" +
            "       book_id, name, author, year, description, price, seller_id, image_id\n" +
            "from cart c\n" +
            "    left join users u on c.user_id = u.id\n" +
            "    left join books b on c.book_id = b.id\n";

    //language=SQL
    private static final String SQL_INSERT = "insert into cart (user_id, book_id) values (?, ?)";

    //language=SQL
    private static final String SQL_SELECT_BY_USER_ID = "select c.id, user_id, first_name, last_name, age, city, password, email, avatar_id,\n" +
            "       book_id, name, author, year, description, price, seller_id, image_id\n" +
            "from cart c\n" +
            "    left join users u on c.user_id = u.id\n" +
            "    left join books b on c.book_id = b.id where user_id = ? order by c.id";

    //language=SQL
    private final static String SQL_DELETE_BY_ID = "delete from cart c where c.id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CartRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Cart> rowMapper = (row, rowNumber) -> Cart.builder()
            .id(row.getInt("id"))
            .user(User.builder()
                    .id(row.getInt("user_id"))
                    .firstName(row.getString("first_name"))
                    .lastName(row.getString("last_name"))
                    .age(row.getInt("age"))
                    .city(row.getString("city"))
                    .email(row.getString("email"))
                    .password(row.getString("password"))
                    .avatarId(row.getInt("avatar_id"))
                    .build())
            .book(Book.builder()
                    .id(row.getInt("book_id"))
                    .name(row.getString("name"))
                    .author(row.getString("author"))
                    .year(row.getInt("year"))
                    .description(row.getString("description"))
                    .price(row.getInt("price"))
                    .sellerId(row.getInt("seller_id"))
                    .imageId(row.getInt("image_id"))
                    .build())
            .build();

    @Override
    public Optional<Cart> findById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Cart> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rowMapper);
    }

    @Override
    public void save(Cart item) {
        if(item.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setInt(1, item.getUser().getId());
                statement.setInt(2, item.getBook().getId());
                return statement;
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                item.setId(keyHolder.getKey().intValue());
            }
        }
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public List<Cart> findByUserId(Integer userId) {
        return jdbcTemplate.query(SQL_SELECT_BY_USER_ID, rowMapper, userId);
    }
}
