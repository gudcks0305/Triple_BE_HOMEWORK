package com.example.triple_be_homework.point.entity;

import com.example.triple_be_homework.common.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(indexes = {
        @Index(name = "idx_user_place_id", columnList = "userId,placeId"),
        @Index(name = "idx_user_id", columnList = "userId"),
        @Index(name = "idx_user_place_pointType_id", columnList = "userId,placeId,pointType"),})
public class PointHistory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    private Byte point;

    @Enumerated(EnumType.STRING)
    private PointType pointType;

    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(columnDefinition = "BINARY(16)")
    private UUID placeId;

    private LocalDateTime expiredDate;


    public PointRemain toPointRemainEntity() {
        return PointRemain.builder()
                .point(this.point)
                .pointType(this.pointType)
                .userId(this.userId)
                .placeId(this.placeId)
                .historyId(this.historyId)
                .expiredDate(this.expiredDate)
                .build();
    }


}
