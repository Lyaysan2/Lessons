package ru.itis.utils.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DiagnosisRecordMapper.class, RecipeMapper.class})
public interface AppointmentDetailsMapper {

    AppointmentDetailsResponse toResponse(AppointmentDetailsEntity details);
}
