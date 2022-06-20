package com.example.triple_be_homework.repository.place;

import com.example.triple_be_homework.entity.Place;
import com.example.triple_be_homework.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlaceRepository extends JpaRepository<Place, String> , CustomPlaceRepository {

    Optional<Place> findByPlaceId(String placeId);
}
