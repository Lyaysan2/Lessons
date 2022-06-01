package ru.itis.utils.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DiagnosisMapper.class)
public interface DiagnosisRecordMapper {

    DiagnosisRecordResponse toResponse(DiagnosisRecordEntity diagnosisRecord);
}
