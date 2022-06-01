package ru.itis.utils.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiagnosisMapper {

    DiagnosisResponse toResponse(DiagnosisEntity diagnosis);
}
