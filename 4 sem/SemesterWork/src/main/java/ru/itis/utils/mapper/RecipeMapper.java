package ru.itis.utils.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DrugMapper.class)
public interface RecipeMapper {

    RecipeResponse toResponse(RecipeEntity recipe);
}