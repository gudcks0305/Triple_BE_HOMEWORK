package com.example.triple_be_homework.dto.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {
    private String content;
    private List<MultipartFile> attachedPhotoIds;
    private String placeId;

}
