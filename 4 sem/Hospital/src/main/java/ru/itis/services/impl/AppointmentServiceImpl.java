package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.ScheduleForm;
import ru.itis.dto.response.AppointmentResponse;
import ru.itis.mapper.AppointmentMapper;
import ru.itis.model.Appointment;
import ru.itis.model.Schedule;
import ru.itis.repositories.AppointmentRepository;
import ru.itis.repositories.ScheduleRepository;
import ru.itis.services.AppointmentService;
import ru.itis.services.DoctorService;
import ru.itis.services.PatientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ScheduleRepository scheduleRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentMapper appointmentMapper;

    @Override
    public void createAppointment(Long doctorId, Long accountId) {
        appointmentRepository.save(Appointment.builder()
                .doctor(doctorService.getById(doctorId))
                .patient(patientService.getById(accountId))
                .build());
    }

    @Override
    public void createSchema(ScheduleForm scheduleForm) {
        scheduleRepository.save(Schedule.builder()
                .appointment(appointmentRepository.getById(scheduleForm.getAppointmentId()))
                .timeStart(scheduleForm.getTimeStart())
                .timeEnd(scheduleForm.getTimeEnd())
                .build());
    }

    @Override
    public List<AppointmentResponse> getAllAppointment() {
        return appointmentMapper.toListResponse(appointmentRepository.findAll());
    }
}
