package com.petdoctor.enrollment.tool.mapper;

import com.petdoctor.enrollment.model.dto.AppointmentDto;
import com.petdoctor.enrollment.model.entity.AppointmentEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-18T23:44:25+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19 (Oracle Corporation)"
)
@Component
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public AppointmentDto appointmentEntityToAppointmentDto(AppointmentEntity appointmentEntity) {
        if ( appointmentEntity == null ) {
            return null;
        }

        AppointmentDto appointmentDto = new AppointmentDto();

        appointmentDto.setId( appointmentEntity.getId() );
        appointmentDto.setStartTime( appointmentEntity.getStartTime() );
        appointmentDto.setAppointmentState( appointmentEntity.getAppointmentState() );
        appointmentDto.setClientId( appointmentEntity.getClientId() );
        appointmentDto.setDoctorId( appointmentEntity.getDoctorId() );

        return appointmentDto;
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
