package com.rungroop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rungroop.models.Event;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "events")
public class ClubDto {
    private Long Id ;
    @NotEmpty(message = "Title not empty !")
    private String title;
    @NotEmpty(message = "PhotoUrl not empty !")
    private String photoUrl;
    @NotEmpty(message = "Content not empty !")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<EventDto> events;
}
