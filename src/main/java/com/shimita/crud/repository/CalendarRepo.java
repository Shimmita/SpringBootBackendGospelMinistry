package com.shimita.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shimita.crud.model.EventsCalendar;

public interface CalendarRepo extends JpaRepository<EventsCalendar, Long> {

}
