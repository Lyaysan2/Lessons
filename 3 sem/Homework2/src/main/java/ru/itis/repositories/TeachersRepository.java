package ru.itis.repositories;

import ru.itis.models.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeachersRepository {
    Optional<Teacher> findById (Integer id);
    List<Teacher> findAll();
    Optional<Teacher> findByFirstName (String firstName);
    void update (Integer id, Teacher teacher);
    void save (Teacher teacher);
    void delete (Integer id);
}
