package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.request.MakeAppointmentRequest;
import ru.itis.dto.response.DoctorResponse;
import ru.itis.dto.response.DoctorScheduleResponse;
import ru.itis.service.DoctorService;

import javax.validation.Valid;
import java.util.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {

    private final DoctorService doctorService;

    @GetMapping(value = "/{department}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorResponse> getDoctorsByDepartment(@PathVariable("department") String department) {
        return doctorService.getDoctorsByDepartment(department);
    }

    @GetMapping(value = "/doctor/{doctor-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public DoctorScheduleResponse getDoctorsSchedule(@PathVariable("doctor-id") UUID userId) {
        return doctorService.getDoctorSchedule(userId);
    }

    @PostMapping(value = "/doctor/{doctor-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UUID makeAppointment(@PathVariable("doctor-id") UUID userId, @Valid @RequestBody MakeAppointmentRequest makeAppointmentRequest) {
        return doctorService.createAppointment(userId, makeAppointmentRequest);
    }
}
