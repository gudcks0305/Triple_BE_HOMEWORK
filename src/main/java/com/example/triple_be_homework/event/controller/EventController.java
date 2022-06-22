package com.example.triple_be_homework.event.controller;

import com.example.triple_be_homework.common.dto.ApiResponse;
import com.example.triple_be_homework.event.dto.EventRequestDto;
import com.example.triple_be_homework.event.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EventController {
    private final EventService eventService;

    @PostMapping("/events")
    public ApiResponse event(@RequestBody EventRequestDto eventDto) {
        log.info("eventDto: {}", eventDto.getContent());
        log.info("eventDto: {}", eventDto.getAttachedPhotoIds());
        log.info(eventDto.getPlaceId());
        log.info(eventDto.getUserId());
        log.info(eventDto.getAction().name());


        eventService.publishPointEvent(eventDto);
        return ApiResponse.success("message"
                , eventDto.getReviewId() + "이벤트 처리 완료 , 처리 action : " + eventDto.getAction());
    }

}
