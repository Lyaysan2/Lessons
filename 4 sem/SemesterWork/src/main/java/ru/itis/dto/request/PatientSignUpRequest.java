package ru.itis.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PatientSignUpRequest extends UserExtendedRequest {

    @Size(min = 5, max = 30, message = "MINIMUM_ADDRESS_SIZE: {min}, MAXIMUM_ADDRESS_SIZE: {max}")
    private String address;

    @Pattern(regexp = "^[1-4][+-]$", message = "BLOOD_TYPE_INVALID")
    private String bloodType;
}