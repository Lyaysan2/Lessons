package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.models.Teacher;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TeachersRepositoryJdbcImpl implements TeachersRepository {

    private static final String SQL_SELECT_BY_ID = "select * from teacher where id = ?";
    private static final String SQL_SELECT_ALL = "select * from teacher";
    private static final String SQL_SELECT_BY_FIRST_NAME = "select * from teacher where first_name = ?";
    private static final String SQL_UPDATE_BY_ID = "update teacher set first_name = ?, last_name = ?, subject = ? where id = ?";
    private static final String SQL_INSERT = "insert into teacher (first_name, last_name, subject, password) values (?, ?, ?, ?)";
    private static final String SQL_DELETE_BY_ID = "delete from teacher where id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TeachersRepositoryJdbcImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Teacher> teacherRowMapper = (row, rowNumber) -> {
        int id = row.getInt("id");
        String firstName = row.getString("first_name");
        String lastName = row.getString("last_name");
        String subject = row.getString("subject");
        String password = row.getString("password");

        return new Teacher(id, firstName, lastName, subject, password);
    };


    @Override
    public Optional<Teacher> findById(Integer id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, teacherRowMapper, id));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Teacher> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, teacherRowMapper);
    }

    @Override
    public Optional<Teacher> findByFirstName(String firstName) {
        try{
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_FIRST_NAME, teacherRowMapper, firstName));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Integer id, Teacher teacher) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, teacher.getFirst_name(), teacher.getLast_name(), teacher.getSubject(), id);
    }

    @Override
    public void save(Teacher teacher) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[] {"id"});

            statement.setString(1, teacher.getFirst_name());
            statement.setString(2, teacher.getLast_name());
            statement.setString(3, teacher.getSubject());
            statement.setString(4, teacher.getPassword());

            return statement;
        }, keyHolder);
        teacher.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }
}
