package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.request.PatientSignUpRequest;
import ru.itis.dto.response.PatientProfileResponse;
import ru.itis.service.PatientService;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientUserController {

    private final PatientService patientService;

    @PostMapping(value = "/sign-up", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createUser(@Valid @RequestBody PatientSignUpRequest patient) {
        return patientService.createUser(patient);
    }

    @PutMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable("userId") UUID userId, @Valid @RequestBody PatientSignUpRequest user) {
        patientService.updateUser(userId, user);
    }

    @GetMapping(value = "/{userId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PatientProfileResponse getPatientProfile(@PathVariable("userId") UUID userId) {
        return patientService.getProfile(userId);
    }
}
