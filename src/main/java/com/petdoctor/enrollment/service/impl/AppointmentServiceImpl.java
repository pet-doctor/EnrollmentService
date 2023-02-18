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

        return mapEntityToDto(appointmentEntity);
    }

    @Override
    public List<AppointmentDto> getAppointmentsByClientId(Long clientId) {

        List<AppointmentEntity> appointmentEntityList =
                appointmentRepository.findAppointmentEntitiesByClientId(clientId).orElse(null);

        if (appointmentEntityList == null) {
            return null;
        }

        return mapEntitiesToDtos(appointmentEntityList);
    }

    @Override
    public AppointmentDto registerAppointment(AppointmentDto appointmentDto) {

        AppointmentEntity appointmentEntity = mapDtoToEntity(appointmentDto);

        if (appointmentEntity == null) {
            throw new EnrollmentServiceMappingException("Exception occurred due appointment mapping");
        }

        appointmentEntity.setAppointmentState(AppointmentState.TAKEN);

        return mapEntityToDto(appointmentEntity);
    }

    @Override
    @Transactional
    public AppointmentDto updateAppointment(Long appointmentId, AppointmentDto appointmentDto) {

        AppointmentEntity appointmentEntity = findAppointmentById(appointmentId);

        if (appointmentEntity.getClientId() != null &&
                appointmentDto.getClientId() != null)
            appointmentEntity.setClientId(appointmentDto.getClientId());

        appointmentEntity.setAppointmentState(appointmentDto.getAppointmentState());
        if (appointmentEntity.getAppointmentState() == AppointmentState.OPEN)
            appointmentEntity.setClientId(null);

        return mapEntityToDto(appointmentEntity);
    }

    private AppointmentEntity findAppointmentById(Long appointmentId) {

        return appointmentRepository
                .findAppointmentEntityById(appointmentId).orElseThrow(() -> {
                    throw new EnrollmentServiceNotFoundException("Appointment with id " + appointmentId + " has not found");
                });
    }

    private AppointmentDto mapEntityToDto(AppointmentEntity appointmentEntity) {

        return appointmentMapper
                .appointmentEntityToAppointmentDto(appointmentEntity);
    }

    private AppointmentEntity mapDtoToEntity(AppointmentDto appointmentDto) {

        return appointmentMapper
                .appointmentDtoToAppointmentEntity(appointmentDto);
    }

    private List<AppointmentDto> mapEntitiesToDtos(List<AppointmentEntity> appointmentEntities) {

        return appointmentEntities
                .stream().map(appointmentMapper::appointmentEntityToAppointmentDto)
                .collect(Collectors.toList());
    }
}
