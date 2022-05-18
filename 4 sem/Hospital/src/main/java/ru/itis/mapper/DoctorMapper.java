package ru.itis.mapper;

import org.mapstruct.Mapper;
import ru.itis.dto.response.DoctorResponse;
import ru.itis.model.Doctor;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorResponse toResponse(Doctor doctor);
}
