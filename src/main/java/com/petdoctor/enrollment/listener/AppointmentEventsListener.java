package com.petdoctor.enrollment.listener;


import com.petdoctor.enrollment.kafka.KafkaConstant;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.kafka.common.protocol.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppointmentEventsListener {

    @KafkaListener(
                    topics = KafkaConstant.KAFKA_ENROLLMENT_OPEN_APPOINTMENT_TOPIC,
                    groupId = KafkaConstant.KAFKA_ENROLLMENT_APPOINTMENT_GROUP
            )
    public void openAppointmentFromDoctorListener(String message) {

        log.info("appointment opened successfully");
        log.info(message);
    }

    @KafkaListener(
            topics = KafkaConstant.KAFKA_ENROLLMENT_CLOSE_APPOINTMENT_TOPIC,
            groupId = KafkaConstant.KAFKA_ENROLLMENT_APPOINTMENT_GROUP
    )
    public void closeAppointmentFromDoctorListener(String message) {

        log.info("appointment closed successfully");
        log.info(message);
    }
}
