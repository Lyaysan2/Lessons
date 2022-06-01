package ru.itis.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PatientProfileResponse extends AccountResponse{

    private Instant birthday;

    private String email;

    private String phone;

    private String address;

    private String passportNumber;

    private String policyNumber;

    private String bloodType;
}