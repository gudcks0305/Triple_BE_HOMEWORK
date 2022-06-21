package com.example.triple_be_homework.service;

import com.example.triple_be_homework.dto.review.ReviewDto;
import com.example.triple_be_homework.entity.AttachedPhoto;
import com.example.triple_be_homework.entity.Place;
import com.example.triple_be_homework.entity.Review;
import com.example.triple_be_homework.entity.User;
import com.example.triple_be_homework.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final AttachedPhotoService attachedPhotoService;

    private final UserService userService;

    private final PlaceService placeService;


    public void save(ReviewDto reviewDto)  {

        List<AttachedPhoto> attachedPhotoIds =  attachedPhotoService.saveAll(reviewDto.getAttachedPhotoIds());
        User user = Optional.ofNullable(userService.findByUserId(reviewDto.getUserId()))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Place place = Optional.ofNullable(placeService.findByPlaceId(reviewDto.getPlaceId()))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 장소입니다."));

        Review review = Review.builder()
                .userId(user)
                .placeId(place)
                .content(reviewDto.getContent())
                .attachedPhotoIds(attachedPhotoIds)
                .build();
        reviewRepository.save(review);
    }
    public List<ReviewDto> findAll() {
        List<Review> reviews = reviewRepository.findAll();

        return reviews.stream()
                .map(ReviewDto::of).collect(Collectors.toList());
    }
}
