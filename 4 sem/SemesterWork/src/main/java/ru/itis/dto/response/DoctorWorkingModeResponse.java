package ru.itis.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorWorkingModeResponse {

    private String dayOfWeek;

    private Instant timeStart;

    private Instant timeEnd;
}