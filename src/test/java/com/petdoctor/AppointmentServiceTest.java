package com.petdoctor;

import com.petdoctor.enrollment.model.dto.AppointmentDto;
import com.petdoctor.enrollment.model.dto.ClientDto;
import com.petdoctor.enrollment.model.entity.AppointmentEntity;
import com.petdoctor.enrollment.model.entity.AppointmentState;
import com.petdoctor.enrollment.repository.AppointmentRepository;
import com.petdoctor.enrollment.service.AppointmentService;
import com.petdoctor.enrollment.tool.exception.EnrollmentServiceNotFoundException;
import com.petdoctor.enrollment.tool.mapper.AppointmentMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AppointmentServiceTest {

    @MockBean
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentMapper appointmentMapper;

    private AppointmentDto appointmentTestDto;

    private ClientDto clientDto1;
    private AppointmentEntity findByClientIdAppointmentTestDto1;
    private AppointmentEntity findByClientIdAppointmentTestDto2;
    private AppointmentEntity findByClientIdAppointmentTestDto3;

    @BeforeEach
    private void setUp() {
        this.appointmentTestDto = AppointmentDto.builder()
                .id(1L)
                .appointmentState(AppointmentState.OPEN)
                .clientId(2L)
                .doctorId(3L)
                .startTime(LocalDateTime.now())
                .build();

        this.clientDto1 = ClientDto.builder()
                .id(1L)
                .name("Biba")
                .surname("Bobov")
                .email("biba@bobov.aboba")
                .petName("Sobak")
                .vetClinicId(1L)
                .build();

        this.findByClientIdAppointmentTestDto1 = appointmentMapper.appointmentDtoToAppointmentEntity(
                AppointmentDto.builder()
                        .id(2L)
                        .appointmentState(AppointmentState.OPEN)
                        .clientId(1L)
                        .doctorId(1L)
                        .startTime(LocalDateTime.now())
                        .build());

        this.findByClientIdAppointmentTestDto2 = appointmentMapper.appointmentDtoToAppointmentEntity(
                AppointmentDto.builder()
                        .id(3L)
                        .appointmentState(AppointmentState.OPEN)
                        .clientId(1L)
                        .doctorId(1L)
                        .startTime(LocalDateTime.now())
                        .build());

        this.findByClientIdAppointmentTestDto3 = appointmentMapper.appointmentDtoToAppointmentEntity(
                AppointmentDto.builder()
                        .id(4L)
                        .appointmentState(AppointmentState.OPEN)
                        .clientId(1L)
                        .doctorId(1L)
                        .startTime(LocalDateTime.now())
                        .build());
    }

    @Test
    public void givenAppointmentRegisteredSuccessfully() {

        AppointmentDto appointmentDto;

        appointmentDto = appointmentService.registerAppointment(appointmentTestDto);

        Assertions.assertEquals(appointmentTestDto.getId(), appointmentDto.getId());
    }

    @Test
    public void givenAppointmentChangedState() {

        AppointmentDto appointmentDto;

        appointmentDto = appointmentService.registerAppointment(appointmentTestDto);

        Assertions.assertEquals(AppointmentState.TAKEN, appointmentDto.getAppointmentState());
    }

    @Test
    public void givenAppointmentNotFoundThrowsException() {

        Assertions.assertThrows(EnrollmentServiceNotFoundException.class,
                () -> appointmentService.getAppointmentById(this.appointmentTestDto.getId()));
    }

    @Test
    public void givenAppointmentFoundByIdSuccessfully() {

        AppointmentDto appointmentDto;
        AppointmentEntity appointmentEntity;
        Long appointmentId = appointmentTestDto.getId();

        appointmentEntity = appointmentMapper
                .appointmentDtoToAppointmentEntity(appointmentTestDto);
        Mockito.when(appointmentRepository
                        .findAppointmentEntityById(appointmentId))
                .thenReturn(Optional.of(appointmentEntity));
        appointmentDto = appointmentService
                .getAppointmentById(appointmentId);

        Assertions.assertNotNull(appointmentDto);
        Assertions.assertEquals(appointmentDto.getId(), appointmentId);
    }

    @Test
    public void givenClientIdAllAppointmentsFoundSuccessfully() {

        List<AppointmentEntity> expectedClient1Appointments = new ArrayList<>();
        expectedClient1Appointments.add(findByClientIdAppointmentTestDto1);
        expectedClient1Appointments.add(findByClientIdAppointmentTestDto2);
        expectedClient1Appointments.add(findByClientIdAppointmentTestDto3);
        List<AppointmentDto> actualAppointments;
        Long client1Id = clientDto1.getId();

        Mockito.when(appointmentRepository
                        .findAppointmentEntitiesByClientId(client1Id))
                .thenReturn(Optional.of(expectedClient1Appointments));
        actualAppointments = appointmentService.getAppointmentsByClientId(client1Id);

        Assertions.assertEquals(expectedClient1Appointments.size(), actualAppointments.size());
    }
}
