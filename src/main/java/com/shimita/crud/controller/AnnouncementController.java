package com.shimita.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shimita.crud.model.Announcement;
import com.shimita.crud.service.AnnouncementService;

@RestController
@RequestMapping("/api/v1/announce")
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;

    //  create
    @PostMapping("/create")
    public ResponseEntity<?> createAnnouncementEntity(@RequestBody Announcement announcement) {

        try {
            announcementService.saveAnnouncement(announcement);
            return ResponseEntity.ok(announcement);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Failed to save announcement: " + e.getMessage()));
        }
    }

    // Get all
    @GetMapping("/all")
    public ResponseEntity<?> getAllAnnouncement() {
        try {
            List<Announcement> announcement = announcementService.getAnnouncements();
            return ResponseEntity.ok(announcement);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Error fetching announcement: " + e.getMessage()));
        }
    }
}
