package com.shimita.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shimita.crud.model.EventsCalendar;
import com.shimita.crud.service.CalendarService;

// ✅ Response wrapper for Android Retrofit compatibility
class ApiResponse {
    private boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}

@RestController
@RequestMapping("/api/v1/events")
public class EventsCalendarController {
    @Autowired
    private CalendarService calendarService;

    // ✅ create event
    @PostMapping("/create")
    public ResponseEntity<?> createEvents(@RequestBody EventsCalendar eventsCalendar) {

        try {
            calendarService.saveEventsCalendar(eventsCalendar);
            return ResponseEntity.ok(eventsCalendar);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Failed to save event calendar: " + e.getMessage()));
        }
    }

    // ✅ Get all events
    @GetMapping("/all")
    public ResponseEntity<?> getAllEventsCalendar() {
        try {
            List<EventsCalendar> eventsCalendars = calendarService.getAllEventsCalendars();
            return ResponseEntity.ok(eventsCalendars);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Error fetching events calendar: " + e.getMessage()));
        }
    }
}
