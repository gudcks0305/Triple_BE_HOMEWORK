package com.example.triple_be_homework.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(indexes = {
        @Index(name = "idx_review_id", columnList = "reviewId")})
public class Review  extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID reviewId;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private User userId;

    @OneToMany(mappedBy = "reviewId" , fetch = FetchType.LAZY)
    private List<AttachedPhoto> attachedPhotoIds;

    @ManyToOne
    @JoinColumn(name = "place_Id")
    private Place placeId;
    @ColumnDefault("0")
    private boolean is_Deleted;
}
