package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import ru.itis.dto.enums.Role;
import ru.itis.dto.request.PatientSignUpRequest;
import ru.itis.dto.response.AccountResponse;
import ru.itis.dto.response.PatientProfileResponse;
import ru.itis.exception.NotFoundException;
import ru.itis.model.AccountEntity;
import ru.itis.model.PatientEntity;
import ru.itis.provider.JwtAccessTokenProvider;
import ru.itis.repositories.PatientRepository;
import ru.itis.service.PatientService;
import ru.itis.service.UserService;
import ru.itis.utils.mapper.PatientMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtAccessTokenProvider accessTokenProvider;
    private final UserService userService;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Override
    public UUID createUser(PatientSignUpRequest patient) {
        if (userService.findByEmail(patient.getEmail()).isEmpty()){
            patient.setPassword(passwordEncoder.encode(patient.getPassword()));
            PatientEntity patientEntity = patientMapper.toEntity(patient);
            patientEntity.getAccount().setRoles(Collections.singleton(accessTokenProvider.getRole(Role.PATIENT)));
            return patientRepository.save(patientEntity).getId();
        } else {
            throw new UserExistException();
        }
    }

    @Override
    public UUID createUser(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),
                authentication.getName());

        String userInfoEndpointUri = client.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUri();

        if (!StringUtils.isEmpty(userInfoEndpointUri)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();

            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());
            HttpEntity entity = new HttpEntity("", headers);
            ResponseEntity<Map> response = restTemplate
                    .exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
            OAuthRequest oAuthRequest = OAuthRequest.builder()
                    .firstName(response.getBody().get("given_name").toString())
                    .lastName(response.getBody().get("family_name").toString())
                    .email(response.getBody().get("email").toString())
                    .build();

            if (userService.findByEmail(oAuthRequest.getEmail()).isEmpty()) {
                oAuthRequest.setPassword(passwordEncoder.encode(RandomStringUtils.randomAlphanumeric(6)));
                PatientEntity patientEntity = patientMapper.toEntity(oAuthRequest);
                patientEntity.getAccount().setRoles(Collections.singleton(accessTokenProvider.getRole(Role.PATIENT)));
                return patientRepository.save(patientEntity).getId();
            } else {
                return userService.loadUserByUsername(oAuthRequest.getEmail()).getId();
            }
        } else {
            throw new NotFoundException("google user not received");
        }
    }

    @Override
    public void updateUser(UUID userId, PatientSignUpRequest user) {
        AccountEntity accountEntity = userService.findUserById(userId);

        accountEntity.setFirstName(user.getFirstName());
        accountEntity.setLastName(user.getLastName());
        accountEntity.setMiddleName(user.getMiddleName());
        accountEntity.setBirthday(user.getBirthday());
        accountEntity.setEmail(user.getEmail());
        accountEntity.setHashPassword(passwordEncoder.encode(user.getPassword()));
        accountEntity.setPhone(user.getPhone());

        PatientEntity patientEntity = accountEntity.getPatient();
        patientEntity.setAddress(user.getAddress());
        patientEntity.setPassportNumber(user.getPassportNumber());
        patientEntity.setPolicyNumber(user.getPolicyNumber());
        patientEntity.setBloodType(user.getBloodType());
        patientEntity.setAccount(accountEntity);

        patientRepository.save(patientEntity);
    }

    @Override
    public PatientProfileResponse getProfile(UUID userId) {
        return patientMapper.toProfileResponse(userService.findUserById(userId));
    }

    @Override
    public List<AccountResponse> getAllPatients() {
        return patientMapper.toListResponse(patientRepository.findAll());
    }
}
