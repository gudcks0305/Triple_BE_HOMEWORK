package com.example.triple_be_homework.point.entity;

import com.example.triple_be_homework.common.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "idx_place_id", columnList = "placeId"),})
public class PlaceReviewCount extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewCountId;

    @Column(columnDefinition = "BINARY(16)")
    private UUID placeId;

    private Integer reviewCount;

    public void addReviewCount() {
        this.reviewCount++;
    }

    public void minusReviewCount() {
        this.reviewCount--;
    }
}
