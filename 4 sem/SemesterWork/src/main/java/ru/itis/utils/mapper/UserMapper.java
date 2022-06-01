package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.itis.dto.response.UserResponse;
import ru.itis.model.AccountEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = RoleMapper.class)
public interface UserMapper {

    UserResponse toResponse(AccountEntity accountEntity);
}
