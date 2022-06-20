package com.example.triple_be_homework.repository.photo;

import com.example.triple_be_homework.entity.AttachedPhoto;
import com.example.triple_be_homework.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PhotoRepository extends JpaRepository<AttachedPhoto, String> , CustomPhotoRepository {

    Optional<AttachedPhoto> findByAttachedPhotoId(UUID attachedPhotoId);
}
