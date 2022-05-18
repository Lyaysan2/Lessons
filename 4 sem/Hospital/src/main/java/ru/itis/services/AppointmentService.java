package ru.itis.services;

import ru.itis.dto.request.ScheduleForm;
import ru.itis.dto.response.AppointmentResponse;

import java.util.List;

public interface AppointmentService {
    void createAppointment(Long doctorId, Long accountId);

    void createSchema(ScheduleForm scheduleForm);

    List<AppointmentResponse> getAllAppointment();
}
