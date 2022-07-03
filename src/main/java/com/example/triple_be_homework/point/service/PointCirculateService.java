package com.example.triple_be_homework.point.service;

import com.example.triple_be_homework.event.dto.EventKafka;
import com.example.triple_be_homework.point.dto.PointHistoryResponseDto;
import com.example.triple_be_homework.point.entity.PlaceReviewCount;
import com.example.triple_be_homework.point.entity.PointHistory;
import com.example.triple_be_homework.point.entity.PointRemain;
import com.example.triple_be_homework.point.entity.PointType;
import com.example.triple_be_homework.point.repository.PlaceReviewCountRepository;
import com.example.triple_be_homework.point.repository.PointHistoryRepository;
import com.example.triple_be_homework.point.repository.PointRemainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointCirculateService {
    private final PointHistoryRepository pointHistoryRepository;
    private final PlaceReviewCountRepository placeReviewCountRepository;
    private final PointRemainRepository pointRemainRepository;

    @Transactional
    public List<PointHistory> createPointHistoryListWhenAddReview(UUID userId, UUID placeId, EventKafka pointEvent) {
        List<PointHistory> pointHistoriesTobeSaved = new ArrayList<>();
        LocalDateTime expiredDate = LocalDateTime.now().plusYears(1L);

        // 조건 1. content 1자 이상 텍스트 작성 (+1)
        if (pointEvent.getContentLength() > 0)
            pointHistoriesTobeSaved.add(PointHistory.builder()
                    .point((byte) 1)
                    .pointType(PointType.CONTENT)
                    .expiredDate(expiredDate)
                    .userId(userId)
                    .placeId(placeId)
                    .build());

        // 조건 2. photo 1장 이상 게시할 경우 (+1)
        if (pointEvent.getPhotosCount() > 0)
            pointHistoriesTobeSaved.add(PointHistory.builder()
                    .point((byte) 1)
                    .pointType(PointType.PHOTO)
                    .expiredDate(expiredDate)
                    .userId(userId)
                    .placeId(placeId)
                    .build());

       // 해당 Place 가 없는 경우 save

        PlaceReviewCount placeReviewCount = placeReviewCountRepository.findByPlaceId(placeId)
                .orElseGet(
                        () -> {
                            PlaceReviewCount reviewCount = PlaceReviewCount
                                    .builder().reviewCount(0)
                                    .placeId(placeId)
                                    .build();
                            placeReviewCountRepository.saveAndFlush(reviewCount);
                            return reviewCount;
                        }
                );
                //.orElseThrow(() -> new PlaceReviewCountNotFoundException(placeId));
        // 조건 3. 해당 장소에 대한 첫 리뷰글인 경우 (+1)
        if (placeReviewCount.getReviewCount() == 0)
            pointHistoriesTobeSaved.add(PointHistory.builder()
                    .point((byte) 1)
                    .pointType(PointType.FIRST)
                    .expiredDate(expiredDate)
                    .userId(userId)
                    .placeId(placeId)
                    .build());

        placeReviewCount.addReviewCount();

        return pointHistoriesTobeSaved;
    }

    @Transactional
    public List<PointHistory> createPointHistoryListWhenModifyReview(UUID userId, UUID placeId, EventKafka pointEvent) {
        List<PointHistory> pointHistoriesTobeSaved = new ArrayList<>();
        LocalDateTime expiredDate = LocalDateTime.now().plusYears(1L);

        pointRemainRepository.findByUserIdAndPlaceIdAndPointType(userId, placeId, PointType.PHOTO)
                .ifPresentOrElse(pointRemain -> {
                    // 조건 1. 글과 사진이 있는 리뷰에 사진을 모두 삭제하는 경우 (-1)
                    if (pointEvent.getPhotosCount() == 0) {
                        pointHistoriesTobeSaved.add(PointHistory.builder()
                                .point((byte) -1)
                                .pointType(PointType.DEL_ALL_PHOTO)
                                .expiredDate(expiredDate)
                                .userId(userId)
                                .placeId(placeId)
                                .build());

                        pointRemainRepository.delete(pointRemain);
                    }
                }, () -> {
                    // 조건 2. 글만 작성한 리뷰에 사진을 게시한 경우 (+1)
                    if (pointEvent.getPhotosCount() > 0) {
                        pointHistoriesTobeSaved.add(PointHistory.builder()
                                .point((byte) 1)
                                .pointType(PointType.PHOTO)
                                .expiredDate(expiredDate)
                                .userId(userId)
                                .placeId(placeId)
                                .build());
                    }
                });

        return pointHistoriesTobeSaved;
    }

    @Transactional
    public List<PointHistory> createPointHistoryListWhenDeleteReview(UUID userId, UUID placeId, List<PointRemain> pointRemainList) {
        List<PointHistory> pointHistoriesTobeSaved = new ArrayList<>();

        // 삭제할 리뷰에 해당하는 기존 유효 포인트 총점 계산
        int totalPointTobeDeleted = pointRemainList.stream().mapToInt(PointRemain::getPoint).sum();

        log.info("삭제 대상 유효 total point: " + totalPointTobeDeleted);

        pointHistoriesTobeSaved.add(PointHistory.builder()
                .point((byte) (totalPointTobeDeleted * -1))
                .pointType(PointType.DEL_REVIEW)
                .userId(userId)
                .placeId(placeId)
                .build());

        // point_remain 내 유효한 리뷰 포인트 모두 삭제
        pointRemainRepository.deleteAll(pointRemainList);

        // place_review_count 해당 장소에 대한 리뷰 카운트 -1
        PlaceReviewCount placeReviewCount = placeReviewCountRepository.findByPlaceId(placeId)
                .orElseThrow(() -> new IllegalArgumentException("Place Not Found error"));

        placeReviewCount.minusReviewCount();

        return pointHistoriesTobeSaved;
    }




}
