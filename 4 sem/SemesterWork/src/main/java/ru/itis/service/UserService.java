package ru.itis.service;

import ru.itis.dto.request.UserRequest;
import ru.itis.dto.response.TokenCoupleResponse;
import ru.itis.dto.response.UserResponse;
import ru.itis.model.AccountEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    AccountEntity findUserById(UUID id);

    Optional<AccountEntity> findByEmail(String email);

    UserResponse loadUserByUsername(String subject);

    TokenCoupleResponse login(UserRequest request);

    void deleteUser(UUID userId);
}
