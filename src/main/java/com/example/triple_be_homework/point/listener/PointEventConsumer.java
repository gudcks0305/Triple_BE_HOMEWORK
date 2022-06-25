package com.example.triple_be_homework.point.listener;

import com.example.triple_be_homework.event.dto.ActionType;
import com.example.triple_be_homework.event.dto.EventKafka;
import com.example.triple_be_homework.point.service.PointCalculateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PointEventConsumer {

    private final PointCalculateService pointCalculateService;

    @KafkaListener(topics = "point_history", groupId = "point")
    public void consumerFromTopic(EventKafka pointEvent) {
        log.info("Point calculate Review Action: " + pointEvent.getAction());
        ActionType action = pointEvent.getAction();

        UUID userId = pointEvent.getUserId();
        UUID placeId = pointEvent.getPlaceId();

        switch (action) {
            case ADD:
                pointCalculateService.addReviewPoint(userId, placeId, pointEvent);
                break;
            case MOD:
                pointCalculateService.modifyReviewPoint(userId, placeId, pointEvent);
                break;
            case DELETE:
                pointCalculateService.deleteReviewPoint(userId, placeId);
                break;
            default:
                throw new RuntimeException("Action Not Found Exception");
        }
    }
}