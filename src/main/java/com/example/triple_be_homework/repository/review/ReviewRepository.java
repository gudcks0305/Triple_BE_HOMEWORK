package com.example.triple_be_homework.repository.review;

import com.example.triple_be_homework.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, String> , CustomReviewRepository {

    Optional<Review> findByReviewId(UUID reviewId);
}
