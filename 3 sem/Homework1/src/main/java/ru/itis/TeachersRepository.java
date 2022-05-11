package ru.itis;

import java.util.List;
import java.util.Optional;

public interface TeachersRepository {
    Optional<Teacher> findById (Integer id);
    List<Teacher> findAll();
    List<Teacher> findBySubject (String subject);
    void update (Integer id, Teacher teacher);
    void save (Teacher teacher);
    void delete (Integer id);
}
