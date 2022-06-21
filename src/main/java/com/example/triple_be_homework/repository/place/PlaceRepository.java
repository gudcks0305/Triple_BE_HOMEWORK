package com.example.triple_be_homework.repository.place;

import com.example.triple_be_homework.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, String> , CustomPlaceRepository {

    Optional<Place> findByPlaceId(String placeId);
}
