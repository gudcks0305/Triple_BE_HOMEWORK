package com.example.triple_be_homework.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachedPhoto extends BaseTimeEntity{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID attachedPhotoId;
    private String fileName;
    private String filePath;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_review_id")
    private Review review;


}
