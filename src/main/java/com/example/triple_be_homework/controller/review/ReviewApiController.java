package com.example.triple_be_homework.controller.review;

import com.example.triple_be_homework.dto.ApiResponse;
import com.example.triple_be_homework.dto.review.ReviewDto;
import com.example.triple_be_homework.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewApiController {
    private final ReviewService reviewService;


    @PostMapping("/review")
    public ApiResponse save(ReviewDto reviewDto)  {
        reviewService.save(reviewDto);

        return ApiResponse.success("data","리뷰가 저장되었습니다.");
    }

}
