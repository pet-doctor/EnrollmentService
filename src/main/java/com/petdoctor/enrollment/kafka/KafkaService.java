package com.petdoctor.enrollment.kafka;

public interface KafkaService {

    void sendMessage(Object payload, String topic);
}
