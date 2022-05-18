package ru.itis.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.dto.request.SignUpForm;
import ru.itis.dto.response.PatientResponse;
import ru.itis.model.Patient;
import ru.itis.model.Role;
import ru.itis.model.State;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient toPatient(PatientResponse patientResponse);

    @Mapping(target = "role", constant = "PATIENT")
    @Mapping(target = "state", constant = "CONFIRMED")
    @Mapping(target = "firstName", source = "signUpForm.firstName")
    @Mapping(target = "lastName", source = "signUpForm.lastName")
    @Mapping(target = "email", source = "signUpForm.email")
    @Mapping(target = "password", source = "signUpForm.password")
    @Mapping(target = "appointments", ignore = true)
    Patient toPatient(SignUpForm signUpForm);

    PatientResponse toResponse(Patient patient);
}
