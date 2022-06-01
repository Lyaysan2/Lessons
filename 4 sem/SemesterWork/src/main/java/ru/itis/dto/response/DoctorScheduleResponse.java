package ru.itis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DoctorScheduleResponse extends DoctorResponse {

    private List<DoctorWorkingModeResponse> workingMode;

    private List<AppointmentTimeResponse> appointmentTime;
}