package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.itis.dto.response.AccountResponse;
import ru.itis.model.AccountEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AccountMapper {

    AccountResponse toResponse(AccountEntity accountEntity);
}
