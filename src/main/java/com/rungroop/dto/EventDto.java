package com.rungroop.dto;

import com.rungroop.models.Club;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private Long id;
    @Size(min = 2, max = 50 , message = "name is not empty")
    private String name ;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime ;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime ;
    @Length(min = 2, max = 50 , message = "type is not empty")
    private String type ;
    private String photoUrl;
    private LocalDateTime createdOn ;
    private LocalDateTime updateOn;
    private Club club ;
}
