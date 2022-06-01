package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.request.DoctorSignUpRequest;
import ru.itis.dto.response.DoctorInformationResponse;
import ru.itis.dto.response.DoctorScheduleResponse;
import ru.itis.dto.response.DoctorProfileResponse;
import ru.itis.dto.response.DoctorResponse;
import ru.itis.model.AccountEntity;
import ru.itis.model.DoctorEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {WorkingModeMapper.class, AppointmentTimeMapper.class, CommentMapper.class})
public interface DoctorMapper {

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
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "employmentDate", ignore = true)
    @Mapping(target = "cabinet", source = "cabinet")
    @Mapping(target = "hospital", ignore = true)
    @Mapping(target = "schedule", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    @Mapping(target = "comments", ignore = true)
    DoctorEntity toEntity(DoctorSignUpRequest doctorRequest);

    @Mapping(target = "id", source = "doctor.account.id")
    @Mapping(target = "firstName", source = "doctor.account.firstName")
    @Mapping(target = "lastName", source = "doctor.account.lastName")
    @Mapping(target = "middleName", source = "doctor.account.middleName")
    @Mapping(target = "department", source = "doctor.department")
    DoctorResponse toResponse(DoctorEntity doctor);

    List<DoctorResponse> toListResponse(List<DoctorEntity> doctorEntityList);

    @Mapping(target = "id", source = "account.id")
    @Mapping(target = "firstName", source = "account.firstName")
    @Mapping(target = "lastName", source = "account.lastName")
    @Mapping(target = "middleName", source = "account.middleName")
    @Mapping(target = "birthday", source = "account.birthday")
    @Mapping(target = "email", source = "account.email")
    @Mapping(target = "phone", source = "account.phone")
    @Mapping(target = "department", source = "account.doctor.department")
    @Mapping(target = "cabinet", source = "account.doctor.cabinet")
    @Mapping(target = "employmentDate", source = "account.doctor.employmentDate")
    DoctorProfileResponse toProfileResponse(AccountEntity account);

    @Mapping(target = "id", source = "account.id")
    @Mapping(target = "firstName", source = "account.firstName")
    @Mapping(target = "lastName", source = "account.lastName")
    @Mapping(target = "middleName", source = "account.middleName")
    @Mapping(target = "department", source = "account.doctor.department")
    @Mapping(target = "workingMode", source = "account.doctor.schedule")
    @Mapping(target = "appointmentTime", source = "account.doctor.appointment")
    DoctorScheduleResponse toScheduleResponse(AccountEntity account);

    @Mapping(target = "id", source = "account.id")
    @Mapping(target = "firstName", source = "account.firstName")
    @Mapping(target = "lastName", source = "account.lastName")
    @Mapping(target = "middleName", source = "account.middleName")
    @Mapping(target = "department", source = "account.doctor.department")
    @Mapping(target = "employmentDate", source = "account.doctor.employmentDate")
    @Mapping(target = "comments", source = "account.doctor.comments")
    DoctorInformationResponse toInformationResponse(AccountEntity account);
}
