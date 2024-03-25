package com.rungroup.web.dto;

import com.rungroup.web.models.Club;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;


    private String type;

    @NotEmpty(message = "You must provide an image")
    private String photoUrl;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Club club;
}