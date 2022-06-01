package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.AppointmentEntity;
import ru.itis.model.DoctorEntity;

import java.time.Instant;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {

    AppointmentEntity findFirstByDateTimeBeforeAndDoctorOrderByDateTimeDesc(Instant time, DoctorEntity doctorEntity);
}
