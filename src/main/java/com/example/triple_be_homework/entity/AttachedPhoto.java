package com.example.triple_be_homework.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(indexes = {
        @Index(name = "idx_attachedPhoto_id", columnList = "attachedPhotoId")})
public class AttachedPhoto extends BaseTimeEntity{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID attachedPhotoId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private Review reviewId;
    public String covertToString() {
        return this.attachedPhotoId.toString();
    }

}
