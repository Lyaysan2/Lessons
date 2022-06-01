package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.dto.response.CommentResponse;
import ru.itis.model.CommentEntity;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "doctorId", source = "comment.doctor.id")
    @Mapping(target = "patientName", source = "comment.patient.account.firstName")
    CommentResponse toResponse(CommentEntity comment);
}
