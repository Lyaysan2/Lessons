package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.enums.Department;
import ru.itis.dto.enums.Role;
import ru.itis.dto.request.CommentRequest;
import ru.itis.dto.request.DoctorSignUpRequest;
import ru.itis.dto.request.DoctorUpdateRequest;
import ru.itis.dto.request.MakeAppointmentRequest;
import ru.itis.dto.response.DoctorInformationResponse;
import ru.itis.dto.response.DoctorProfileResponse;
import ru.itis.dto.response.DoctorResponse;
import ru.itis.dto.response.DoctorScheduleResponse;
import ru.itis.model.AccountEntity;
import ru.itis.model.AppointmentEntity;
import ru.itis.model.CommentEntity;
import ru.itis.model.DoctorEntity;
import ru.itis.provider.JwtAccessTokenProvider;
import ru.itis.repositories.DoctorRepository;
import ru.itis.service.*;
import ru.itis.utils.mapper.DoctorMapper;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final UserService userService;
    private final CommentService commentService;
    private final AppointmentService appointmentService;
    private final HospitalService hospitalService;
    private final FileService fileService;
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtAccessTokenProvider jwtAccessTokenProvider;

    @Override
    public UUID createUser(DoctorSignUpRequest doctor) {
        if (userService.findByEmail(doctor.getEmail()).isEmpty()){
            doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
            DoctorEntity doctorEntity = doctorMapper.toEntity(doctor);
            doctorEntity.setEmploymentDate(Instant.now());
            doctorEntity.setHospital(hospitalService.findByName(doctor.getHospital()));
            doctorEntity.getAccount().setRoles(Collections.singleton(jwtAccessTokenProvider.getRole(Role.DOCTOR)));
            return doctorRepository.save(doctorEntity).getId();
        } else {
            throw new UserExistException();
        }
    }

    @Override
    public List<DoctorResponse> getAllDoctors() {
        return doctorMapper.toListResponse(doctorRepository.findAll());
    }

    @Override
    public void updateUser(UUID userId, DoctorUpdateRequest user) {
        AccountEntity accountEntity = userService.findUserById(userId);

        accountEntity.setFirstName(user.getFirstName());
        accountEntity.setLastName(user.getLastName());
        accountEntity.setMiddleName(user.getMiddleName());
        accountEntity.setBirthday(user.getBirthday());
        accountEntity.setEmail(user.getEmail());
        accountEntity.setHashPassword(passwordEncoder.encode(user.getPassword()));
        accountEntity.setPhone(user.getPhone());
        accountEntity.setTimeChange(Instant.now());

        DoctorEntity doctorEntity = accountEntity.getDoctor();

        if (Objects.nonNull(user.getPhotoId())){
            doctorEntity.setPhoto(fileService.findFile(user.getPhotoId()));
        }
        doctorEntity.setAccount(accountEntity);
        doctorEntity.setDepartment(user.getDepartment());
        doctorEntity.setCabinet(user.getCabinet());
        doctorRepository.save(doctorEntity);
    }

    @Override
    public DoctorProfileResponse getProfile(UUID userId) {
        return doctorMapper.toProfileResponse(userService.findUserById(userId));
    }

    @Override
    public List<DoctorResponse> getDoctorsByDepartment(String department) {
        return doctorMapper.toListResponse(doctorRepository.getByDepartment(Department.valueOf(department)));
    }

    @Override
    public DoctorScheduleResponse getDoctorSchedule(UUID userId) {
        return doctorMapper.toScheduleResponse(userService.findUserById(userId));
    }

    @Override
    public DoctorInformationResponse getDoctorInformation(UUID userId) {
        return doctorMapper.toInformationResponse(userService.findUserById(userId));
    }

    @Override
    public void createComment(UUID userId, CommentRequest commentRequest) {
        CommentEntity commentEntity = CommentEntity.builder()
                .doctor(userService.findUserById(userId).getDoctor())
                .patient(userService.findUserById(commentRequest.getPatientId()).getPatient())
                .text(commentRequest.getText())
                .createdAt(Instant.now())
                .build();

        commentService.saveComment(commentEntity);
    }

    @Override
    public UUID createAppointment(UUID userId, MakeAppointmentRequest makeAppointmentRequest) {
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .doctor(userService.findUserById(userId).getDoctor())
                .patient(userService.findUserById(makeAppointmentRequest.getPatientId()).getPatient())
                .dateTime(makeAppointmentRequest.getTime())
                .build();

        return appointmentService.saveAppointment(appointmentEntity);
    }
}
