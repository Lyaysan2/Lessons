package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import ru.itis.model.AppointmentEntity;

@Mapper(componentModel = "spring", uses = {DoctorMapper.class, AppointmentDetailsMapper.class})
public interface AppointmentMapper {

    AppointmentResponse toResponse(AppointmentEntity appointmentEntity);
}
