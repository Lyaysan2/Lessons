package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from users where id = ?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from users";
    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL = "select * from users where email = ?";
    //language=SQL
    private static final String SQL_INSERT = "insert into users (first_name, last_name, age, city, email, password, avatar_id) values (?, ?, ?, ?, ?, ?, ?)";
    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from users where id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<User> userRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("id");
        String firstName = row.getString("first_name");
        String lastName = row.getString("last_name");
        Integer age = row.getInt("age");
        String city = row.getString("city");
        String email = row.getString("email");
        String password = row.getString("password");
        Integer avatar_id = row.getInt("avatar_id");

        return new User(id, firstName, lastName, age, city, email, password, avatar_id);
    };

    @Override
    public Optional<User> findById(Integer id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, userRowMapper, id));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public void save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[] {"id"});

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getAge());
            statement.setString(4, user.getCity());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPassword());
            statement.setInt(7, user.getAvatarId());

            return statement;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try{
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
