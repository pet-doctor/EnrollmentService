package com.petdoctor.enrollment.service;

import com.petdoctor.enrollment.model.dto.AppointmentDto;
import com.petdoctor.enrollment.model.entity.AppointmentState;

import java.util.List;

public interface AppointmentService {

    AppointmentDto getAppointmentById(Long appointmentId);
    List<AppointmentDto> getAppointmentsByClientId(Long clientId);
    AppointmentDto registerAppointment(AppointmentDto appointmentDto);
    AppointmentDto changeAppointmentState(AppointmentDto appointmentDto, AppointmentState appointmentState);
    AppointmentDto openAppointment(AppointmentDto appointmentDto);
    AppointmentDto takeAppointment(AppointmentDto appointmentDto);
    AppointmentDto closeAppointment(AppointmentDto appointmentDto);
    AppointmentDto cancelAppointment(AppointmentDto appointmentDto);
}
