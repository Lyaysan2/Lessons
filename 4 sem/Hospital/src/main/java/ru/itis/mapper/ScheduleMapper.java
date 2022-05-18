package ru.itis.mapper;

import org.mapstruct.Mapper;
import ru.itis.dto.response.ScheduleResponse;
import ru.itis.model.Schedule;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    ScheduleResponse toResponse(Schedule schedule);
}
