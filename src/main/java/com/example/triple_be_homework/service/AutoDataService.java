package com.example.triple_be_homework.service;

import com.example.triple_be_homework.entity.AttachedPhoto;
import com.example.triple_be_homework.entity.Place;
import com.example.triple_be_homework.entity.Review;
import com.example.triple_be_homework.entity.User;
import com.example.triple_be_homework.repository.photo.PhotoRepository;
import com.example.triple_be_homework.repository.place.PlaceRepository;
import com.example.triple_be_homework.repository.review.ReviewRepository;
import com.example.triple_be_homework.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AutoDataService {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final PhotoRepository photoRepository;
    private final ReviewRepository reviewRepository;
    public void autoData() {
        List<User> users = new ArrayList<>();
        List<Place> places = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();
        List<AttachedPhoto> photos = new ArrayList<>();
        for(int i=0; i<1; i++) {
            User user = new User();
            users.add(user);

            Place place = new Place();
            places.add(place);

            Review review = new Review();
            review.setContent("리뷰입니다.");
            review.setPlaceId(place);
            review.setUserId(user);

            reviews.add(review);
            AttachedPhoto photo = new AttachedPhoto();

            photos.add(photo);
        }
        reviewRepository.saveAll(reviews);
        photoRepository.saveAll(photos);
        userRepository.saveAll(users);
        placeRepository.saveAll(places);
    }
}
