package com.petdoctor.enrollment.api;

import com.petdoctor.enrollment.kafka.KafkaConstant;
import com.petdoctor.enrollment.kafka.KafkaService;
import com.petdoctor.enrollment.model.dto.AppointmentDto;
import com.petdoctor.enrollment.model.entity.AppointmentState;
import com.petdoctor.enrollment.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/petdoctor/enrollment/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final KafkaService kafkaService;

    @GetMapping
    public ResponseEntity<?> getAppointment(@RequestParam Long appointmentId) {

        try {

            AppointmentDto appointmentDto = appointmentService.getAppointmentById(appointmentId);

            if (appointmentDto == null) {

                return ResponseEntity.notFound().build();
            }

            kafkaService.sendMessage(appointmentDto, KafkaConstant.KAFKA_ENROLLMENT_APPOINTMENT_TOPIC);
            return ResponseEntity.ok(appointmentDto);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAppointmentsByClientId(@RequestParam Long clientId) {

        try {

            List<AppointmentDto> appointmentDtoList = appointmentService.getAppointmentsByClientId(clientId);

            if (appointmentDtoList == null) {

                return ResponseEntity.notFound().build();
            }

            kafkaService.sendMessage(appointmentDtoList, KafkaConstant.KAFKA_ENROLLMENT_APPOINTMENT_TOPIC);
            return ResponseEntity.ok(appointmentDtoList);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAppointment(@RequestBody AppointmentDto appointmentDto) {

        try {

            AppointmentDto registeredAppointmentDto = appointmentService.registerAppointment(appointmentDto);

            if (registeredAppointmentDto == null) {

                return ResponseEntity.badRequest().build();
            }

            kafkaService.sendMessage(registeredAppointmentDto, KafkaConstant.KAFKA_ENROLLMENT_APPOINTMENT_TOPIC);
            return ResponseEntity.ok(registeredAppointmentDto);
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/cancel")
    public ResponseEntity<?> cancelAppointment(@RequestParam Long appointmentId) {

        try {

            AppointmentDto appointmentDto = appointmentService.cancelAppointment(AppointmentDto.builder()
                    .id(appointmentId)
                    .appointmentState(AppointmentState.CANCELED)
                    .build());

            if (appointmentDto == null) {

                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

}
