package ru.itis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.model.Schedule;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentResponse {

    private Long id;

    private PatientResponse patient;

    private DoctorResponse doctor;

    private ScheduleResponse schedule;
}
