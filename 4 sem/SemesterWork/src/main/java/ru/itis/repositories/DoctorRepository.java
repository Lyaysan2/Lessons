package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dto.enums.Department;
import ru.itis.model.DoctorEntity;

import java.util.List;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<DoctorEntity, UUID> {
    List<DoctorEntity> getByDepartment(Department department);
}
