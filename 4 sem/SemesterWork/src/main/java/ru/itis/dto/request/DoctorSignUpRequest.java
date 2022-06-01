package ru.itis.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.dto.enums.Department;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSignUpRequest extends UserExtendedRequest{

    @NotNull
    private Department department;

    @Pattern(regexp = "^[0-9]{2,4}$", message = "CABINET_NUMBER_INVALID")
    private String cabinet;

    @NotBlank
    private String hospital;
}