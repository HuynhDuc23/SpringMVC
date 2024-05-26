package com.rungroop.service.impl;

import com.rungroop.dto.EventDto;
import com.rungroop.models.Club;
import com.rungroop.models.Event;
import com.rungroop.repository.ClubRepository;
import com.rungroop.repository.EventRepository;
import com.rungroop.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    private ClubRepository clubRepository ;
    private EventRepository eventRepository ;
    @Autowired
    public EventServiceImpl(ClubRepository clubRepository, EventRepository eventRepository) {
        this.clubRepository = clubRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    private Event mapToEvent(EventDto eventDto) {
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
}
