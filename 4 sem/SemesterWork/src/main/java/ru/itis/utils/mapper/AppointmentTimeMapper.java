package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.model.AppointmentEntity;

@Mapper(componentModel = "spring")
public interface AppointmentTimeMapper {

    @Mapping(target = "dateTime", source = "appointment.dateTime")
    AppointmentTimeResponse toResponse(AppointmentEntity appointment);
}