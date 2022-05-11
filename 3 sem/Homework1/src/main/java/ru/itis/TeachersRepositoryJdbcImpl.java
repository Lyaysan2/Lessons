package ru.itis;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeachersRepositoryJdbcImpl implements TeachersRepository {

    private static final String SQL_SELECT_BY_ID = "select * from teacher where id = ?";
    private static final String SQL_SELECT_ALL = "select * from teacher";
    private static final String SQL_SELECT_BY_SUBJECT = "select * from teacher where subject = ?";
    private static final String SQL_UPDATE_BY_ID = "update teacher set first_name = ?, last_name = ?, experience = ?, subject = ? where id = ?";
    private static final String SQL_INSERT = "insert into teacher (first_name, last_name, experience, subject) values (?, ?, ?, ?)";
    private static final String SQL_DELETE_BY_ID = "delete from teacher where id = ?";

    private JdbcTemplate jdbcTemplate;

    public TeachersRepositoryJdbcImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Teacher> teacherRowMapper = (row, rowNumber) -> {
        int id = row.getInt("id");
        String firstName = row.getString("first_name");
        String lastName = row.getString("last_name");
        int experience = row.getInt("experience");
        String subject = row.getString("subject");

        return new Teacher(id, firstName, lastName, experience, subject);
    };


    private final ResultSetExtractor<List<Teacher>> resultSetExtractor = rs -> {
        List<Teacher> teachers = new ArrayList<>();
        Teacher teacher = null;
        while(rs.next()){
                teacher = new Teacher(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("experience"), rs.getString("subject"));
                teachers.add(teacher);
        }
        return teachers;
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
        return jdbcTemplate.query(SQL_SELECT_ALL, resultSetExtractor);
    }

    @Override
    public List<Teacher> findBySubject(String subject) {
        return jdbcTemplate.query(SQL_SELECT_BY_SUBJECT, resultSetExtractor, subject);
    }

    @Override
    public void update(Integer id, Teacher teacher) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, teacher.getFirst_name(), teacher.getLast_name(), teacher.getExperience(), teacher.getSubject(), id);
    }

    @Override
    public void save(Teacher teacher) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[] {"id"});

            statement.setString(1, teacher.getFirst_name());
            statement.setString(2, teacher.getLast_name());
            statement.setInt(3, teacher.getExperience());
            statement.setString(4, teacher.getSubject());

            return statement;
        }, keyHolder);
        teacher.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }
}
