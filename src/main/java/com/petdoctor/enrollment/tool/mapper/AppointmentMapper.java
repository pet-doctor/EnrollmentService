package com.petdoctor.enrollment.tool.mapper;

import com.petdoctor.enrollment.model.dto.AppointmentDto;
import com.petdoctor.enrollment.model.entity.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mappings(value = {
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "startTime", target = "startTime"),
            @Mapping(source = "appointmentState", target = "appointmentState"),
            @Mapping(source = "clientId", target = "clientId"),
            @Mapping(source = "doctorId", target = "doctorId")
    })
    AppointmentDto appointmentEntityToAppointmentDto(AppointmentEntity appointmentEntity);

    @Mappings(value = {
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "startTime", target = "startTime"),
            @Mapping(source = "appointmentState", target = "appointmentState"),
            @Mapping(source = "clientId", target = "clientId"),
            @Mapping(source = "doctorId", target = "doctorId")
    })
    AppointmentEntity appointmentDtoToAppointmentEntity(AppointmentDto appointmentDto);
}
