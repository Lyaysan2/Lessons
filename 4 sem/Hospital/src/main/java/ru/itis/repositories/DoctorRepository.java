package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
