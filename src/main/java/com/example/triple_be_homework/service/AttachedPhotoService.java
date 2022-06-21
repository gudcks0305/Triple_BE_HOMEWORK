package com.example.triple_be_homework.service;

import com.example.triple_be_homework.entity.AttachedPhoto;
import com.example.triple_be_homework.repository.photo.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AttachedPhotoService {
    private final PhotoRepository photoRepository;

    public List<AttachedPhoto> saveAll(List<String> attachedPhotoIds)  {
         List <AttachedPhoto> attachedPhotos = attachedPhotoIds.stream().map(id -> {
             AttachedPhoto attachedPhoto = new AttachedPhoto();
             attachedPhoto.setAttachedPhotoId(UUID.fromString(id));
             return attachedPhoto;
         }).collect(Collectors.toList());
            return photoRepository.saveAll(attachedPhotos);

    }

}
