package ru.itis.utils.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DrugMapper {

    DrugResponse toResponse(DrugEntity drug);
}
