package com.example.triple_be_homework.point.service;

import com.example.triple_be_homework.point.dto.PointTotalResponseDto;
import com.example.triple_be_homework.point.repository.PointRemainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointTotalService {

    private final PointRemainRepository pointRemainRepository;

    @Transactional(readOnly = true)
    public PointTotalResponseDto findTotalPoint(UUID userId) {

        Integer currentUserPoint = pointRemainRepository.sumPointByUserId(userId);

        return PointTotalResponseDto.builder().totalRemainPoint(currentUserPoint).build();
    }



}