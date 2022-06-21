package com.example.triple_be_homework.controller;


import com.example.triple_be_homework.dto.ApiResponse;
import com.example.triple_be_homework.entity.Review;
import com.example.triple_be_homework.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class testController  {
    private final ReviewService reviewService;
    @GetMapping("/test")
    public ApiResponse test( ) {
        return ApiResponse.success("data",reviewService.findAll());
    }
}
