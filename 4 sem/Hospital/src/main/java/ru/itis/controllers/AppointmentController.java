package ru.itis.controllers;

import com.auth0.jwt.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.request.ScheduleForm;
import ru.itis.dto.response.AppointmentResponse;
import ru.itis.services.AppointmentService;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/{doctorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAppointment(@PathVariable Long doctorId, Authentication authentication) {
        Long accountId = Long.valueOf(JWT.decode(authentication.getPrincipal().toString()).getSubject());
        appointmentService.createAppointment(doctorId, accountId);
    }

    @PostMapping("/schedule")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAppointmentDate(@RequestBody ScheduleForm scheduleForm){
        appointmentService.createSchema(scheduleForm);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> getAllAppointment(){
        return appointmentService.getAllAppointment();
    }
}
