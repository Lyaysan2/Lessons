package ru.itis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.response.AppointmentResponse;
import ru.itis.model.Appointment;
import ru.itis.model.Schedule;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PatientMapper.class, DoctorMapper.class, ScheduleMapper.class})
public interface AppointmentMapper {

    @Mapping(target = "patient", source = "appointment.patient")
    @Mapping(target = "doctor", source = "appointment.doctor")
    @Mapping(target = "schedule", source = "appointment.schedule")
    AppointmentResponse toResponse(Appointment appointment);

    List<AppointmentResponse> toListResponse(List<Appointment> appointments);
}
