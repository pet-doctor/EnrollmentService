package com.petdoctor.enrollment.tool.mapper;

import com.petdoctor.enrollment.model.dto.AppointmentDto;
import com.petdoctor.enrollment.model.dto.AppointmentDto.AppointmentDtoBuilder;
import com.petdoctor.enrollment.model.entity.AppointmentEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-08T22:43:44+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19 (Oracle Corporation)"
)
@Component
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public AppointmentDto appointmentEntityToAppointmentDto(AppointmentEntity appointmentEntity) {
        if ( appointmentEntity == null ) {
            return null;
        }

        AppointmentDtoBuilder appointmentDto = AppointmentDto.builder();

        appointmentDto.id( appointmentEntity.getId() );
        appointmentDto.startTime( appointmentEntity.getStartTime() );
        appointmentDto.appointmentState( appointmentEntity.getAppointmentState() );
        appointmentDto.clientId( appointmentEntity.getClientId() );
        appointmentDto.doctorId( appointmentEntity.getDoctorId() );

        return appointmentDto.build();
    }

    @Override
    public AppointmentEntity appointmentDtoToAppointmentEntity(AppointmentDto appointmentDto) {
        if ( appointmentDto == null ) {
            return null;
        }

        AppointmentEntity appointmentEntity = new AppointmentEntity();

        appointmentEntity.setId( appointmentDto.getId() );
        appointmentEntity.setStartTime( appointmentDto.getStartTime() );
        appointmentEntity.setAppointmentState( appointmentDto.getAppointmentState() );
        appointmentEntity.setClientId( appointmentDto.getClientId() );
        appointmentEntity.setDoctorId( appointmentDto.getDoctorId() );

        return appointmentEntity;
    }
}
