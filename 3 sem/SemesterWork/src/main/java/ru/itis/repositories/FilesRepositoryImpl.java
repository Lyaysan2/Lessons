package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.itis.models.FileInfo;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class FilesRepositoryImpl implements FilesRepository{

    //language=SQL
    private final static String SQL_SELECT_ALL = "select * from files";
    //language=SQL
    private final static String SQL_INSERT = "insert into files(storage_file_name, original_file_name, type, size) " +
            "values (?, ?, ?, ?)";
    //language=SQL
    private final static String SQL_UPDATE = "update files set storage_file_name = ?, original_file_name = ?, type = ?, size = ? where id = ?";
    //language=SQL
    private final static String SQL_SELECT_BY_ID = "select * from files where id = ?";
    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from files where id = ?";
    //language=SQL
    private static final String SQL_SELECT_BY_STORAGE_NAME = "select * from files where storage_file_name = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public FilesRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<FileInfo> fileRowMapper = (row, rowNumber) ->
            FileInfo.builder()
                    .id(row.getInt("id"))
                    .originalFileName(row.getString("original_file_name"))
                    .storageFileName(row.getString("storage_file_name"))
                    .size(row.getLong("size"))
                    .type(row.getString("type"))
                    .build();


    @Override
    public Optional<FileInfo> findById(Integer id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, fileRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(FileInfo item) {
        if(item.getId() == null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[]{"id"});
                statement.setString(1, item.getStorageFileName());
                statement.setString(2, item.getOriginalFileName());
                statement.setString(3, item.getType());
                statement.setLong(4, item.getSize());
                return statement;
            }, keyHolder);
            item.setId(keyHolder.getKey().intValue());
        }
    }

    @Override
    public Optional<FileInfo> findByStorageName(String name) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_STORAGE_NAME, fileRowMapper, name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<FileInfo> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, fileRowMapper);
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }
}
