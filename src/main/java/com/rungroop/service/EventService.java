package com.rungroop.service;

import com.rungroop.dto.EventDto;

public interface EventService {
    public void createEvent(Long clubId , EventDto eventDto);
}
