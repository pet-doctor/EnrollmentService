package com.petdoctor.enrollment.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.petdoctor.enrollment.model.entity.AppointmentState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("start_time")
    private LocalDate startTime;

    @JsonProperty("appointment_state")
    private AppointmentState appointmentState;

    @JsonProperty("client_id")
    private Long clientId;

    @JsonProperty("doctor_id")
    private Long doctorId;
}
