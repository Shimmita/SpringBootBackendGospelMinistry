package com.shimita.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimita.crud.model.EventsCalendar;
import com.shimita.crud.repository.CalendarRepo;

@Service
public class CalendarService {
    @Autowired
    private CalendarRepo calendarRepo;

    // save event calendar
    public EventsCalendar saveEventsCalendar(EventsCalendar eventsCalendar) {
        return calendarRepo.save(eventsCalendar);
    }

    // get all events
    public List<EventsCalendar> getAllEventsCalendars() {
        return calendarRepo.findAll();
    }
}
