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

    public List<AttachedPhoto> saveAll(List<MultipartFile> attachedPhotoIds) throws IOException {
        String basePath = "upload-files/";
        if(!(new File(basePath)).exists()) {
            (new File(basePath)).mkdir();
        }
        List<AttachedPhoto> attachedPhotos = attachedPhotoIds.stream()
                .map(attachedPhotoId -> {
                    String fileName = UUID.randomUUID().toString();
                    String filePath = basePath + fileName;
                    File file = new File(filePath);
                    try {
                        attachedPhotoId.transferTo(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return AttachedPhoto.builder()
                            .fileName(fileName)
                            .filePath(filePath)
                            .build();
                    }).collect(Collectors.toList());


        photoRepository.saveAll(attachedPhotos);
        return attachedPhotos;

    }

}
