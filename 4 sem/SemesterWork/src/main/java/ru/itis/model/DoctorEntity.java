package ru.itis.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.dto.enums.Department;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false, exclude = {"schedule", "appointment", "comments"})
@Entity
@Table(name = "doctor")
public class DoctorEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;

    private Instant employmentDate;

    private String cabinet;

    @Enumerated(EnumType.STRING)
    private Department department;

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<ScheduleEntity> schedule;

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<AppointmentEntity> appointment;

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<CommentEntity> comments;
}
