package com.example.triple_be_homework.point.repository;

import com.example.triple_be_homework.point.entity.PointRemain;
import com.example.triple_be_homework.point.entity.PointType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PointRemainRepository extends JpaRepository<PointRemain, Long> {

    List<PointRemain> findAllByUserIdAndPlaceId(UUID userId, UUID placeId);

    Optional<PointRemain> findByUserIdAndPlaceIdAndPointType(UUID userId, UUID placeId, PointType pointType);
    // local mysql 버전 문제로 인해 create ddl 시 entity 속성에 하이픈 처리가 되어 수정 .. MY sql 자동 업데이트 되어서 ㅠ...
    @Query(value = "SELECT IFNULL(sum(point), 0) FROM point_remain WHERE user_id = :userId", nativeQuery = true)
    Integer sumPointByUserId(UUID userId);

}
