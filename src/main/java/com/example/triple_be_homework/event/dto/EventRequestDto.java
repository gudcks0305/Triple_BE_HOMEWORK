package com.example.triple_be_homework.event.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class EventRequestDto {
    private String userId;
    private String placeId;

    private String reviewId;
    private ActionType action;
    private EventType type;
    private String content;
    private List<String> attachedPhotoIds;

    public EventKafka toEntity() {
         EventKafka eventKafka = EventKafka.builder()
                 .userId(UUID.fromString(userId))
                 .placeId(UUID.fromString(placeId))
                 .action(action)
                 .photosCount(attachedPhotoIds.size())
                .type(type)
                 .reviewId(UUID.fromString(reviewId))
                 .contentLength(content.length())
                .build();


        return eventKafka;
    }
}
