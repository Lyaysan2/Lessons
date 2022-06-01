package ru.itis.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    @NotNull
    private UUID patientId;

    @Size(min = 3, max = 300, message = "MINIMUM_COMMENT_SIZE: {min}, MAXIMUM_COMMENT_SIZE: {max}")
    private String text;
}