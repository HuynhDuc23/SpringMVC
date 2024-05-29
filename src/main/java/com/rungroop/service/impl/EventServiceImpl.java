package com.rungroop.service.impl;

import com.rungroop.dto.EventDto;
import com.rungroop.mapper.EventMapper;
import com.rungroop.models.Club;
import com.rungroop.models.Event;
import com.rungroop.repository.ClubRepository;
import com.rungroop.repository.EventRepository;
import com.rungroop.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Event event = EventMapper.mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event->EventMapper.mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventDto findEventById(Long id) {
        Event event = eventRepository.findById(id).get();
        return EventMapper.mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event =  EventMapper.mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void eventDelete(Long id) {
        eventRepository.deleteById(id);
    }


}
