package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.model.PatientEntity;

@Mapper(componentModel = "spring", uses = {AccountMapper.class, AppointmentMapper.class})
public interface CardMapper {

    @Mapping(target = "patient", source = "patientEntity.account")
    @Mapping(target = "bloodType", source = "patientEntity.bloodType")
    @Mapping(target = "appointment", source = "patientEntity.appointment")
    CardResponse toResponse(PatientEntity patientEntity);
}
