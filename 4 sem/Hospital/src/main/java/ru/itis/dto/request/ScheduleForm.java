package ru.itis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.model.Appointment;

import javax.persistence.Column;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleForm {

    private Long appointmentId;

    private Instant timeStart;

    private Instant timeEnd;
}
