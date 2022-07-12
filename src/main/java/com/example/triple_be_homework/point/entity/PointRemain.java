package com.example.triple_be_homework.point.entity;

import com.example.triple_be_homework.common.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "point_remain" ,
        indexes = {
        @Index(name = "idx_user_id", columnList = "userId"),})
public class PointRemain extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long remainId;

    private Byte point;

    @Enumerated(EnumType.STRING)
    private PointType pointType;

    @Column(columnDefinition = "BINARY(16)" )
    private UUID userId;

    @Column(columnDefinition = "BINARY(16)")
    private UUID placeId;

    private Long historyId;

    private LocalDateTime expiredDate;

}