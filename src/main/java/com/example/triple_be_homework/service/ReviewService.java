package com.example.triple_be_homework.service;

import com.example.triple_be_homework.dto.review.ReviewRequestDto;
import com.example.triple_be_homework.entity.AttachedPhoto;
import com.example.triple_be_homework.entity.Place;
import com.example.triple_be_homework.entity.Review;
import com.example.triple_be_homework.entity.User;
import com.example.triple_be_homework.repository.photo.PhotoRepository;
import com.example.triple_be_homework.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final AttachedPhotoService attachedPhotoService;

    private final UserService userService;

    private final PlaceService placeService;


    public void save(ReviewRequestDto reviewRequestDto , String userId) throws IOException {

        List<AttachedPhoto> attachedPhotoIds =  attachedPhotoService.saveAll(reviewRequestDto.getAttachedPhotoIds());
        User user = Optional.ofNullable(userService.findByUserId(userId))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Place place = Optional.ofNullable(placeService.findByPlaceId(reviewRequestDto.getPlaceId()))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 장소입니다."));

        Review review = Review.builder()
                .user(user)
                .placeId(place)
                .content(reviewRequestDto.getContent())
                .attachedPhotoIds(attachedPhotoIds)
                .build();
        reviewRepository.save(review);
    }
}
