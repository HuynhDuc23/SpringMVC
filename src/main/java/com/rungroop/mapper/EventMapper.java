package com.rungroop.mapper;

import com.rungroop.dto.EventDto;
import com.rungroop.models.Event;

public class EventMapper {
    public static  Event mapToEvent(EventDto eventDto) {
        Event event = Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .type(eventDto.getType())
                .photoUrl(eventDto.getPhotoUrl())
                .createdOn(eventDto.getCreatedOn())
                .updateOn(eventDto.getUpdateOn())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .build();
        return event;
    }
    public static  EventDto mapToEventDto(Event event) {
        return  EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .type(event.getType())
                .photoUrl(event.getPhotoUrl())
                .createdOn(event.getCreatedOn())
                .updateOn(event.getUpdateOn())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .build();

    }
}
