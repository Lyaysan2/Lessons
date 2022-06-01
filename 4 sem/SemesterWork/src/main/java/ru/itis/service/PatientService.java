package ru.itis.service;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import ru.itis.dto.request.PatientSignUpRequest;
import ru.itis.dto.response.AccountResponse;
import ru.itis.dto.response.PatientProfileResponse;

import java.util.List;
import java.util.UUID;

public interface PatientService{

    UUID createUser(PatientSignUpRequest patient);

    UUID createUser(OAuth2AuthenticationToken authentication);

    void updateUser(UUID userId, PatientSignUpRequest user);

    PatientProfileResponse getProfile(UUID userId);

    List<AccountResponse> getAllPatients();
}
