package com.example.triple_be_homework.dto.review;

import com.example.triple_be_homework.entity.AttachedPhoto;
import com.example.triple_be_homework.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private String reviewId;
    private String userId;
    private String content;
    private List<String> attachedPhotoIds;
    private String placeId;

    public static ReviewDto of(Review review) {
        return ReviewDto.builder()
                .reviewId(review.getReviewId().toString())
                .userId(review.getUserId().getUserId().toString())
                .content(review.getContent())
                .attachedPhotoIds(review.getAttachedPhotoIds().stream().map(AttachedPhoto::covertToString).collect(Collectors.toList()))
                .placeId(review.getPlaceId().getPlaceId().toString())
                .build();
    }
}
