package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.request.DoctorSignUpRequest;
import ru.itis.dto.request.DoctorUpdateRequest;
import ru.itis.dto.response.DoctorProfileResponse;
import ru.itis.service.DoctorService;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorUserController {

    private final DoctorService doctorService;

    @PostMapping(value = "/sign-up", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createUser(@Valid @RequestBody DoctorSignUpRequest doctor) {
        return doctorService.createUser(doctor);
    }

    @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable("userId") UUID userId, @Valid @RequestBody DoctorUpdateRequest user) {
        doctorService.updateUser(userId, user);
    }

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public DoctorProfileResponse getDoctorProfile(@PathVariable("userId") UUID userId) {
        return doctorService.getProfile(userId);
    }
}