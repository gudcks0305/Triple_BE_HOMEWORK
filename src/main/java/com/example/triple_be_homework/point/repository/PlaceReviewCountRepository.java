package com.example.triple_be_homework.point.repository;

import com.example.triple_be_homework.point.entity.PlaceReviewCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;
import java.util.UUID;

public interface PlaceReviewCountRepository extends JpaRepository<PlaceReviewCount, Long> {
    // WRITE LOCK 부여
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="3000")})
    Optional<PlaceReviewCount> findByPlaceId(UUID placeId);
}
