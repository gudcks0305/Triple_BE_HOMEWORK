package com.example.triple_be_homework.point.controller;

import com.example.triple_be_homework.common.dto.ApiResponse;
import com.example.triple_be_homework.point.service.PointCirculateService;
import com.example.triple_be_homework.point.service.PointHistoryService;
import com.example.triple_be_homework.point.service.PointTotalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PointController {
    private final PointHistoryService pointHistoryService;
    private final PointTotalService pointTotalService;

    @GetMapping("/point/total/{userId}")
    public ApiResponse getPointTotal( @PathVariable String userId) {
        log.info("getPointTotal userId : {}", userId);
        return ApiResponse.success("data",pointTotalService.findTotalPoint(UUID.fromString(userId)));
    }
    @GetMapping("/point/history/{userId}")
    public ApiResponse getPointHistory( @PathVariable String userId) {
        return ApiResponse.success("data",pointHistoryService.findPointHistory(UUID.fromString(userId)));
    }
}
