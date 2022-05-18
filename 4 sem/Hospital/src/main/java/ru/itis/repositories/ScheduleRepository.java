package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
