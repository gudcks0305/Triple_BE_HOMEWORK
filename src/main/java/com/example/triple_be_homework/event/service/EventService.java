package com.example.triple_be_homework.event.service;

import com.example.triple_be_homework.event.dto.EventRequestDto;
import com.example.triple_be_homework.event.message.PointEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {
    private final PointEventProducer pointEventProducer;

    public void publishPointEvent(EventRequestDto resource) {

        pointEventProducer.send(resource.toEntity());
    }



}
