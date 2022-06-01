package ru.itis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.request.CommentRequest;
import ru.itis.dto.response.DoctorInformationResponse;
import ru.itis.dto.response.DoctorResponse;
import ru.itis.service.DoctorService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/info")
public class DoctorInfoController {

    private final DoctorService doctorService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorResponse> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping(value = "/{doctor-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public DoctorInformationResponse getDoctorInformation(@PathVariable("doctor-id") UUID userId) {
        return doctorService.getDoctorInformation(userId);
    }

    @PostMapping(value = "/{doctor-id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@PathVariable("doctor-id") UUID userId, @Valid @RequestBody CommentRequest commentRequest) {
        doctorService.createComment(userId, commentRequest);
    }
}
