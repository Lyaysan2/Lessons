package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
