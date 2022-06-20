package com.example.triple_be_homework.controller.review;

import com.example.triple_be_homework.auth.entity.UserPrincipal;
import com.example.triple_be_homework.dto.ApiResponse;
import com.example.triple_be_homework.dto.review.ReviewRequestDto;
import com.example.triple_be_homework.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ReviewApiController {
    private final ReviewService reviewService;


    @PostMapping("/review")
    public ApiResponse save(ReviewRequestDto reviewRequestDto , @AuthenticationPrincipal User userPrincipal) throws IOException {
        reviewService.save(reviewRequestDto, userPrincipal.getUsername());

        return ApiResponse.success("data","리뷰가 저장되었습니다.");
    }

}
