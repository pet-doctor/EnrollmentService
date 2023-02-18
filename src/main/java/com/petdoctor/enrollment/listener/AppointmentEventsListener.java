package com.petdoctor.enrollment.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.petdoctor.enrollment.kafka.KafkaConstant;
import com.petdoctor.enrollment.model.dto.AppointmentDto;
import com.petdoctor.enrollment.model.entity.AppointmentState;
import com.petdoctor.enrollment.service.AppointmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class AppointmentEventsListener {

    private final ObjectMapper objectMapper;
    private final AppointmentService appointmentService;

    @KafkaListener(
            topics = KafkaConstant.KAFKA_ENROLLMENT_OPEN_APPOINTMENT_TOPIC,
            groupId = KafkaConstant.KAFKA_ENROLLMENT_APPOINTMENT_GROUP
    )
    public void openAppointmentFromDoctorListener(Message<String> message) {

        JsonNode messageNode = readMessage(message);

        AppointmentDto appointmentDto = constructAppointmentDtoFromNode(messageNode);
        appointmentDto.setClientId(null);
        appointmentDto.setAppointmentState(AppointmentState.OPEN);

        appointmentService.updateAppointment(appointmentDto.getId(), appointmentDto);

        log.info("appointment opened successfully");
    }

    @KafkaListener(
            topics = KafkaConstant.KAFKA_ENROLLMENT_CLIENT_TOPIC,
            groupId = KafkaConstant.KAFKA_ENROLLMENT_CLIENT_GROUP
    )
    public void enrollClientFromDoctorListener(Message<String> message) {

        JsonNode messageNode = readMessage(message);

        AppointmentDto appointmentDto = constructAppointmentDtoFromNode(messageNode);
        appointmentDto.setAppointmentState(AppointmentState.TAKEN);

        appointmentService.updateAppointment(appointmentDto.getId(), appointmentDto);

        log.info("client has been enrolled successfully");
    }

    @KafkaListener(
            topics = KafkaConstant.KAFKA_ENROLLMENT_CLOSE_APPOINTMENT_TOPIC,
            groupId = KafkaConstant.KAFKA_ENROLLMENT_APPOINTMENT_GROUP
    )
    public void closeAppointmentFromDoctorListener(Message<String> message) {

        JsonNode messageNode = readMessage(message);

        AppointmentDto appointmentDto = constructAppointmentDtoFromNode(messageNode);
        appointmentDto.setAppointmentState(AppointmentState.CLOSED);

        appointmentService.updateAppointment(appointmentDto.getId(), appointmentDto);

        log.info("appointment closed successfully");
        log.info(messageNode.toString());
    }

    private JsonNode readMessage(Message<String> message) {

        JsonNode messageNode = null;
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String payloadJson = ow.writeValueAsString(message.getPayload());
            messageNode = objectMapper.readTree(payloadJson);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

        return messageNode;
    }

    private AppointmentDto constructAppointmentDtoFromNode(JsonNode messageNode) {

        return AppointmentDto.builder()
                .id(messageNode.get("appointment").asLong())
                .appointmentState(AppointmentState.OPEN)
                .doctorId(messageNode.get("doctor").asLong())
                .clientId(messageNode.get("client").asLong())
                .build();
    }
}
