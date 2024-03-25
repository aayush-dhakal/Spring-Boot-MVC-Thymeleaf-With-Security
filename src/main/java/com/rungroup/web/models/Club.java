package com.rungroup.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clubs")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // here IDENTITY type will generate a unique id
    private Long id;
    private String title;
    private String photoUrl;
    private String content;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    //    The Club entity has a One-To-Many relationship with the Event entity in this case. We used mappedBy with the name club in the @OneToMany annotation — this actually refers to the club property in the Event entity. As a result, the club is now referring to the event entity.
//    Basically this creates a One-to-many relation between Club and Event and now Event will have a club_id as foreign key
    @OneToMany(mappedBy = "club", cascade = CascadeType.REMOVE)
    private List<Event> events = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;
}
