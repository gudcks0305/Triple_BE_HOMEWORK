package com.example.triple_be_homework.point.repository;

import com.example.triple_be_homework.point.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
}