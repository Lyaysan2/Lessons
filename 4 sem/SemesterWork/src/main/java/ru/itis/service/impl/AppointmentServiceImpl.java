package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.model.AppointmentEntity;
import ru.itis.model.DoctorEntity;
import ru.itis.repositories.AppointmentRepository;
import ru.itis.service.AppointmentService;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public UUID saveAppointment(AppointmentEntity appointmentEntity) {
        return appointmentRepository.save(appointmentEntity).getId();
    }

    @Override
    public AppointmentEntity findByDateTimeBeforeAndDoctor(Instant date, DoctorEntity doctor) {
        return appointmentRepository.findFirstByDateTimeBeforeAndDoctorOrderByDateTimeDesc(date, doctor);
    }
}
