package com.petdoctor.enrollment.service.impl;

import com.petdoctor.enrollment.model.dto.AppointmentDto;
import com.petdoctor.enrollment.model.entity.AppointmentEntity;
import com.petdoctor.enrollment.model.entity.AppointmentState;
import com.petdoctor.enrollment.repository.AppointmentRepository;
import com.petdoctor.enrollment.service.AppointmentService;
import com.petdoctor.enrollment.tool.exception.EnrollmentServiceMappingException;
import com.petdoctor.enrollment.tool.exception.EnrollmentServiceNotFoundException;
import com.petdoctor.enrollment.tool.mapper.AppointmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentDto getAppointmentById(Long appointmentId) {

        AppointmentEntity appointmentEntity = findAppointmentById(appointmentId);

        if (appointmentEntity == null) {
            return null;
        }

        return appointmentMapper
                .appointmentEntityToAppointmentDto(appointmentEntity);
    }

    @Override
    public List<AppointmentDto> getAppointmentsByClientId(Long clientId) {

        List<AppointmentEntity> appointmentEntityList =
                appointmentRepository.findAppointmentEntitiesByClientId(clientId).orElse(null);

        if (appointmentEntityList == null) {
            return null;
        }

        return appointmentEntityList
                .stream().map(appointmentMapper::appointmentEntityToAppointmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDto registerAppointment(AppointmentDto appointmentDto) {

        AppointmentEntity appointmentEntity =
                appointmentMapper.appointmentDtoToAppointmentEntity(appointmentDto);

        if (appointmentEntity == null) {
            throw new EnrollmentServiceMappingException("Exception occurred due appointment mapping");
        }

        appointmentEntity.setAppointmentState(AppointmentState.TAKEN);

        return appointmentMapper
                .appointmentEntityToAppointmentDto(
                        appointmentRepository.save(appointmentEntity));
    }

    @Override
    @Transactional
    public AppointmentDto changeAppointmentState(AppointmentDto appointmentDto, AppointmentState state) {

        AppointmentEntity appointmentEntity = findAppointmentById(appointmentDto.getId());

        if (appointmentEntity == null) {
            throw new EnrollmentServiceNotFoundException("Appointment with id " + appointmentDto.getId() + " has not found");
        }

        appointmentEntity.setAppointmentState(state);

        // TODO: refactor - talantlivo))))
        if (appointmentEntity.getClientId() != null)
            appointmentEntity.setClientId(appointmentDto.getClientId());

        return appointmentMapper
                .appointmentEntityToAppointmentDto(
                        appointmentRepository.save(appointmentEntity));
    }

    @Override
    public AppointmentDto openAppointment(AppointmentDto appointmentDto) {

        AppointmentEntity appointmentEntity = findAppointmentById(appointmentDto.getId());

        if (appointmentEntity == null) {
            throw new EnrollmentServiceNotFoundException("Appointment has not found");
        }

        appointmentEntity.setAppointmentState(AppointmentState.OPEN);

        return appointmentMapper
                .appointmentEntityToAppointmentDto(
                        appointmentRepository.save(appointmentEntity));
    }

    @Override
    public AppointmentDto takeAppointment(AppointmentDto appointmentDto) {
        AppointmentEntity appointmentEntity = findAppointmentById(appointmentDto.getId());

        if (appointmentEntity == null) {
            throw new EnrollmentServiceNotFoundException("Appointment has not found");
        }

        appointmentEntity.setAppointmentState(AppointmentState.TAKEN);

        return appointmentMapper
                .appointmentEntityToAppointmentDto(
                        appointmentRepository.save(appointmentEntity));
    }

    @Override
    public AppointmentDto closeAppointment(AppointmentDto appointmentDto) {
        AppointmentEntity appointmentEntity = findAppointmentById(appointmentDto.getId());

        if (appointmentEntity == null) {
            throw new EnrollmentServiceNotFoundException("Appointment has not found");
        }

        appointmentEntity.setAppointmentState(AppointmentState.CLOSED);

        return appointmentMapper
                .appointmentEntityToAppointmentDto(
                        appointmentRepository.save(appointmentEntity));
    }

    @Override
    @Transactional
    public AppointmentDto cancelAppointment(AppointmentDto appointmentDto) {

        AppointmentEntity appointmentEntity = findAppointmentById(appointmentDto.getId());

        if (appointmentEntity == null) {
            throw new EnrollmentServiceNotFoundException("Appointment has not found");
        }

        appointmentEntity.setAppointmentState(AppointmentState.CANCELED);

        return appointmentMapper
                .appointmentEntityToAppointmentDto(
                        appointmentRepository.save(appointmentEntity));
    }

    private AppointmentEntity findAppointmentById(Long appointmentId) {
        return appointmentRepository.findAppointmentEntityById(appointmentId).orElse(null);
    }
}
