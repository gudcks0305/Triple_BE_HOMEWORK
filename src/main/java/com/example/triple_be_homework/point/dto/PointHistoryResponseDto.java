package com.example.triple_be_homework.point.dto;

import com.example.triple_be_homework.point.entity.PointHistory;
import com.example.triple_be_homework.point.entity.PointType;
import lombok.Builder;
import lombok.Data;


import java.util.UUID;

@Data
@Builder
public class PointHistoryResponseDto {
    private Byte point;
    private PointType pointType;
    private UUID placeId;

    public static PointHistoryResponseDto of(PointHistory pointHistory) {
        return PointHistoryResponseDto.builder()
                .point(pointHistory.getPoint())
                .pointType(pointHistory.getPointType())
                .placeId(pointHistory.getPlaceId())
                .build();
    }
}
