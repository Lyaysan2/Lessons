package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import ru.itis.dto.response.DoctorWorkingModeResponse;
import ru.itis.model.ScheduleEntity;

@Mapper(componentModel = "spring")
public interface WorkingModeMapper {

    DoctorWorkingModeResponse toResponse(ScheduleEntity schedule);
}
