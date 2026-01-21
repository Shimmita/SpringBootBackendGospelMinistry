package com.shimita.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shimita.crud.model.Announcement;

public interface AnnouncementRepo extends JpaRepository<Announcement,Long> {
    
}
