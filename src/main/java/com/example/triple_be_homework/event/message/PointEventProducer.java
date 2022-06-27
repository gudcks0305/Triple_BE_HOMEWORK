package com.example.triple_be_homework.event.message;

import com.example.triple_be_homework.event.dto.EventKafka;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PointEventProducer {
    public static final String TOPIC = "point_history";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(EventKafka event) {
        log.info("kafka message: " + event);
        kafkaTemplate.send(TOPIC, event);
    }
}