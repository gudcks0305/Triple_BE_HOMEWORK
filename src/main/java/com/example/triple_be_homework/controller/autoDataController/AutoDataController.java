package com.example.triple_be_homework.controller.autoDataController;

import com.example.triple_be_homework.dto.ApiResponse;
import com.example.triple_be_homework.service.AutoDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AutoDataController {
    private final AutoDataService autoDataService;

    @PostMapping("/autoData")
    public ApiResponse autoData( ) {
        autoDataService.autoData();
        return ApiResponse.success("data","자동으로 생성된 데이터가 저장되었습니다.");
    }
}
