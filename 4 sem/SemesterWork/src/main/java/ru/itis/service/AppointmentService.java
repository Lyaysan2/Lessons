package ru.itis.service;

import ru.itis.model.AppointmentEntity;
import ru.itis.model.DoctorEntity;

import java.time.Instant;
import java.util.UUID;

public interface AppointmentService {

    UUID saveAppointment(AppointmentEntity appointmentEntity);

    AppointmentEntity findByDateTimeBeforeAndDoctor(Instant date, DoctorEntity doctor);
}
