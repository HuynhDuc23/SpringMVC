package com.rungroop.dto;

import com.rungroop.models.Event;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
