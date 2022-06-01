package ru.itis.models;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    /** Начало приема */
    @Column(name = "time_start")
    private Instant timeStart;

    /** Конец приема */
    @Column(name = "time_end")
    private Instant timeEnd;
}
