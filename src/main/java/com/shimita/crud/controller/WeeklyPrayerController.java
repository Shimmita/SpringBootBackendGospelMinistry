package com.shimita.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shimita.crud.model.WeeklyPrayer;
import com.shimita.crud.service.WeeklyService;

@RestController
@RequestMapping("/api/v1/weekly")
public class WeeklyPrayerController {
    @Autowired
    private WeeklyService weeklyService;

    @PostMapping("/create")
    public ResponseEntity<?> createAnnouncementEntity(@RequestBody WeeklyPrayer weeklyPrayer) {

        try {
            weeklyService.saveWeeklyPrayer(weeklyPrayer);
            return ResponseEntity.ok(weeklyPrayer);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Failed to save weekly prayer: " + e.getMessage()));
        }
    }

    // ✅ Get all weekly prayers
    @GetMapping("/all")
    public ResponseEntity<?> getAllWeeklyPrayers() {
        try {
            List<WeeklyPrayer> prayers = weeklyService.getAllWeeklyPrayers();
            return ResponseEntity.ok(prayers);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Error fetching prayers: " + e.getMessage()));
        }
    }
}
