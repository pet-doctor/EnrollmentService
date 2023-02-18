package com.petdoctor.enrollment.service;

import com.petdoctor.enrollment.model.dto.AppointmentDto;
import com.petdoctor.enrollment.model.entity.AppointmentState;

import java.util.List;

public interface AppointmentService {

    AppointmentDto getAppointmentById(Long appointmentId);
    List<AppointmentDto> getAppointmentsByClientId(Long clientId);
    AppointmentDto registerAppointment(AppointmentDto appointmentDto);
    AppointmentDto updateAppointment(Long appointmentId, AppointmentDto appointmentDto);
}
