package com.petdoctor.enrollment.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.petdoctor.enrollment.model.entity.AppointmentState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDto implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("appointment_state")
    private AppointmentState appointmentState;

    @JsonProperty("client_id")
    private Long clientId;

    @JsonProperty("doctor_id")
    private Long doctorId;

    @Override
    public String toString() {
        return "AppointmentDto{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", appointmentState=" + appointmentState +
                ", clientId=" + clientId +
                ", doctorId=" + doctorId +
                '}';
    }
}
