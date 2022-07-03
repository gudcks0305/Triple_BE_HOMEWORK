package com.example.triple_be_homework.point.service;

import com.example.triple_be_homework.event.dto.EventKafka;
import com.example.triple_be_homework.point.dto.PointHistoryResponseDto;
import com.example.triple_be_homework.point.entity.PointHistory;
import com.example.triple_be_homework.point.entity.PointRemain;
import com.example.triple_be_homework.point.entity.PointType;
import com.example.triple_be_homework.point.repository.PointHistoryRepository;
import com.example.triple_be_homework.point.repository.PointRemainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointHistoryService {

    private static final String CAL_POINT_COMPLETE_WHEN_ADD_REVIEW
            = "[addReviewPoint] 리뷰 생성에 따른 포인트 이력 정산 완료";
    private static final String CAL_POINT_COMPLETE_WHEN_MOD_REVIEW
            = "[modifyReviewPoint] 리뷰 수정에 따른 포인트 이력 정산 완료";
    private static final String CAL_POINT_COMPLETE_WHEN_DELETE_REVIEW
            = "[deleteReviewPoint] 리뷰 삭제에 따른 포인트 이력 정산 완료";

    private final PointRemainRepository pointRemainRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final PointCirculateService pointCirculateService;



    @Transactional
    public void addReviewPoint(UUID userId, UUID placeId, EventKafka pointEvent) {

        // 1.내 기존 유효한 포인트 존재 여부 체크
        checkPointRemain(userId, placeId);
        // 2.포인트 이력 저장 & point_remain 유효한 포인트 데이터 저장
        List<PointHistory> pointHistoryList
                = pointCirculateService.createPointHistoryListWhenAddReview(userId, placeId, pointEvent);
        saveAllPointHistoryAndRemainList(pointHistoryList);
        log.info(CAL_POINT_COMPLETE_WHEN_ADD_REVIEW);
    }


    @Transactional
    public void modifyReviewPoint(UUID userId, UUID placeId, EventKafka pointEvent) {
        // 1.  내 기존 유효한 포인트 존재 여부 체크
        checkPointRemain(userId, placeId);

        // 2.포인트 이력 저장 & point_remain 유효한 포인트 데이터 저장
        List<PointHistory> pointHistoryList
                = pointCirculateService.createPointHistoryListWhenModifyReview(userId, placeId, pointEvent);
        saveAllPointHistoryAndRemainList(pointHistoryList);

        log.info(CAL_POINT_COMPLETE_WHEN_MOD_REVIEW);
    }


    @Transactional
    public void deleteReviewPoint(UUID userId, UUID placeId) {

        // 1. 내 기존 유효한 포인트 존재 여부 체크
        List<PointRemain> pointRemainList = checkPointRemain(userId, placeId);
        // 2. 포인트 이력 저장 & point_remain 유효한 포인트 데이터 저장
        List<PointHistory> pointHistoryList
                = pointCirculateService.createPointHistoryListWhenDeleteReview(userId, placeId, pointRemainList);
        saveAllPointHistoryAndRemainList(pointHistoryList);

        log.info(CAL_POINT_COMPLETE_WHEN_DELETE_REVIEW);
    }

    private List<PointRemain> checkPointRemain(UUID userId, UUID placeId) {
        List<PointRemain> pointRemainList = pointRemainRepository.findAllByUserIdAndPlaceId(userId, placeId);
        if (pointRemainList.isEmpty())
            throw new RuntimeException(userId + "는 " + placeId + "에 리뷰 기록이 없습니다." );
        return pointRemainList;
    }
     private void saveAllPointHistoryAndRemainList(List<PointHistory> pointHistoryList) {
        // point_history 포인트 이력 저장
        List<PointHistory> pointHistoriesSaved
                = pointHistoryRepository.saveAll(pointHistoryList);

        // point_remain 유효한 포인트 데이터 저장
        pointRemainRepository.saveAll(convertToPointRemainList(pointHistoriesSaved));
    }


    private List<PointRemain> convertToPointRemainList(List<PointHistory> pointHistoryList) {
        return pointHistoryList.stream()
                .filter(pointHistory
                        -> (pointHistory.getPointType() != PointType.DEL_ALL_PHOTO)
                        && (pointHistory.getPointType() != PointType.DEL_REVIEW))
                .map(PointHistory::toPointRemainEntity)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<PointHistoryResponseDto> findPointHistory(UUID fromString) {
        List<PointHistoryResponseDto> pointHistoryResponseDtoList = new ArrayList<>();
        List<PointHistory> pointHistoryList = pointHistoryRepository.findAllByUserId(fromString);
        pointHistoryList.forEach(pointHistory -> {
            PointHistoryResponseDto pointHistoryResponseDto = PointHistoryResponseDto.of(pointHistory);
            pointHistoryResponseDtoList.add(pointHistoryResponseDto);
        });
        return pointHistoryResponseDtoList;
    }

}