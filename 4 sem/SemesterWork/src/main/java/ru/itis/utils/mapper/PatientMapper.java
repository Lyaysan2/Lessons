package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.request.PatientSignUpRequest;
import ru.itis.dto.response.AccountResponse;
import ru.itis.dto.response.PatientProfileResponse;
import ru.itis.model.AccountEntity;
import ru.itis.model.PatientEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "account.firstName", source = "firstName")
    @Mapping(target = "account.lastName", source = "lastName")
    @Mapping(target = "account.middleName", source = "middleName")
    @Mapping(target = "account.birthday", source = "birthday")
    @Mapping(target = "account.email", source = "email")
    @Mapping(target = "account.hashPassword", source = "password")
    @Mapping(target = "account.timeChange", ignore = true)
    @Mapping(target = "account.phone", source = "phone")
    @Mapping(target = "account.roles", ignore = true)
    @Mapping(target = "account.doctor", ignore = true)
    @Mapping(target = "account.patient", ignore = true)
    @Mapping(target = "account.accountRefreshToken", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    @Mapping(target = "vaccinations", ignore = true)
    @Mapping(target = "comments", ignore = true)
    PatientEntity toEntity(PatientSignUpRequest userRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "account.firstName", source = "firstName")
    @Mapping(target = "account.lastName", source = "lastName")
    @Mapping(target = "account.email", source = "email")
    @Mapping(target = "account.hashPassword", source = "password")
    @Mapping(target = "account.timeChange", ignore = true)
    @Mapping(target = "account.roles", ignore = true)
    @Mapping(target = "account.doctor", ignore = true)
    @Mapping(target = "account.patient", ignore = true)
    @Mapping(target = "account.accountRefreshToken", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "passportNumber", ignore = true)
    @Mapping(target = "policyNumber", ignore = true)
    @Mapping(target = "bloodType", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    @Mapping(target = "vaccinations", ignore = true)
    @Mapping(target = "comments", ignore = true)
    PatientEntity toEntity(OAuthRequest oAuthRequest);

    @Mapping(target = "id", source = "patient.account.id")
    @Mapping(target = "firstName", source = "patient.account.firstName")
    @Mapping(target = "lastName", source = "patient.account.lastName")
    @Mapping(target = "middleName", source = "patient.account.middleName")
    AccountResponse toResponse(PatientEntity patient);

    List<AccountResponse> toListResponse(List<PatientEntity> patientList);

    @Mapping(target = "id", source = "account.id")
    @Mapping(target = "firstName", source = "account.firstName")
    @Mapping(target = "lastName", source = "account.lastName")
    @Mapping(target = "middleName", source = "account.middleName")
    @Mapping(target = "birthday", source = "account.birthday")
    @Mapping(target = "email", source = "account.email")
    @Mapping(target = "phone", source = "account.phone")
    @Mapping(target = "address", source = "account.patient.address")
    @Mapping(target = "passportNumber", source = "account.patient.passportNumber")
    @Mapping(target = "policyNumber", source = "account.patient.policyNumber")
    @Mapping(target = "bloodType", source = "account.patient.bloodType")
    PatientProfileResponse toProfileResponse(AccountEntity account);
}
