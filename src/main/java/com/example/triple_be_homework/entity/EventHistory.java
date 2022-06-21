package com.example.triple_be_homework.entity;

import com.example.triple_be_homework.dto.ActionType;
import com.example.triple_be_homework.dto.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(indexes = {
        @Index(name = "idx_event_id", columnList = "eventId")})
public class EventHistory {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review reviewId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place placeId;
    // Add , mod , delete
    @Column(nullable = false)
    private ActionType action;
    // review ~~
    @Column(nullable = false)
    private EventType type;

    @ColumnDefault("0")
    private int numberOfContents;
    @ColumnDefault("0")
    private int photoNum;

}
