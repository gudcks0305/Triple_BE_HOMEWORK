package com.example.triple_be_homework.event.dto;

import com.example.triple_be_homework.common.entity.BaseTimeEntity;
import com.example.triple_be_homework.event.dto.ActionType;
import com.example.triple_be_homework.event.dto.EventType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Builder
@Getter
@Setter
public class EventKafka  {
    private UUID eventId;
    private UUID userId;
    private UUID placeId;
    private ActionType action;
    private UUID reviewId;
    private EventType type;
    private ActionType actionType;
    private Integer contentLength;
    private Integer photosCount;

}
