package com.shimita.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimita.crud.model.Announcement;
import com.shimita.crud.repository.AnnouncementRepo;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepo announcementRepo;

    // save nature
    public Announcement saveAnnouncement(Announcement announcement) {
        if (!announcement.getUsername().isBlank() || !announcement.getRole().isBlank()
                || !announcement.getDetails().isBlank()) {
            return announcementRepo.save(announcement);
        } else
            return announcement;

    }

    // get all
    public List<Announcement> getAnnouncements() {
        return announcementRepo.findAll();
    }
}
