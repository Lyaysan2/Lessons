package ru.itis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "schedule")
public class ScheduleEntity {

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    /** День недели */
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    /** Начало рабочего дня */
    private Instant timeStart;

    /** Конец рабочего дня */
    private Instant timeEnd;

}
