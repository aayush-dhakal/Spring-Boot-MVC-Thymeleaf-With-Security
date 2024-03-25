package com.rungroup.web.models;

import com.rungroup.web.models.Club;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String type;
    private String photoUrl;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    // We have created relationships with the Club of the Event entity using the @ManyToOne annotation. We defined the club_id column with @JoinColumn; this will actually point to the Club.
//    Basically, club_id is the foreign key in Event which refers to the Club entity for Many-to-one relationship between Event to Club
    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false) //  nullable = false infers that event always must have a club_id
    private Club club;
}