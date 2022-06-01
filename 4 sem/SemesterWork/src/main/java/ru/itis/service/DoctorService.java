package ru.itis.service;

import ru.itis.dto.request.CommentRequest;
import ru.itis.dto.request.DoctorSignUpRequest;
import ru.itis.dto.request.DoctorUpdateRequest;
import ru.itis.dto.request.MakeAppointmentRequest;
import ru.itis.dto.response.DoctorInformationResponse;
import ru.itis.dto.response.DoctorProfileResponse;
import ru.itis.dto.response.DoctorResponse;
import ru.itis.dto.response.DoctorScheduleResponse;

import java.util.List;
import java.util.UUID;

public interface DoctorService{

    UUID createUser(DoctorSignUpRequest doctor);

    List<DoctorResponse> getAllDoctors();

    void updateUser(UUID userId, DoctorUpdateRequest user);

    DoctorProfileResponse getProfile(UUID userId);

    List<DoctorResponse> getDoctorsByDepartment(String department);

    DoctorScheduleResponse getDoctorSchedule(UUID userId);

    DoctorInformationResponse getDoctorInformation(UUID userId);

    void createComment(UUID userId, CommentRequest commentRequest);

    UUID createAppointment(UUID userId, MakeAppointmentRequest makeAppointmentRequest);
}
