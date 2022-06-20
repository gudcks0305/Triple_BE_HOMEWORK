package com.example.triple_be_homework.service;

import com.example.triple_be_homework.entity.Place;
import com.example.triple_be_homework.repository.place.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    @Transactional
    public Place findByPlaceId(String placeId) {
        return placeRepository.findByPlaceId(placeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 장소입니다."));
    }

}
