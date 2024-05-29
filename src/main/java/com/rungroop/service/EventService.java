package com.rungroop.service;

import com.rungroop.dto.EventDto;

import java.util.List;

public interface EventService {
    public void createEvent(Long clubId , EventDto eventDto);
    public List<EventDto> findAllEvents();
    public EventDto findEventById(Long id);
    public void updateEvent(EventDto eventDto);
    public void eventDelete(Long id);
}
