package com.example.triple_be_homework.event.dto;

import com.example.triple_be_homework.common.entity.BaseTimeEntity;
import com.example.triple_be_homework.event.dto.ActionType;
import com.example.triple_be_homework.event.dto.EventType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EventKafka  {

    @NotNull
    private UUID userId;
    @NotNull
    private UUID placeId;
    @NotNull
    private ActionType action;
    @NotNull
    private UUID reviewId;
    @NotNull
    private EventType type;
    @NotNull
    private Integer contentLength;
    @NotNull
    private Integer photosCount;

}
