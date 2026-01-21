package com.shimita.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shimita.crud.model.DailyPrayer;
import com.shimita.crud.model.User;
import com.shimita.crud.service.DailyPrayerService;

// ✅ same ApiResponse as used in UserController
class ApiResponse {
    private boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}

@RestController
@RequestMapping("/api/v1/daily")
public class DailyPrayerController {

    @Autowired
    private DailyPrayerService dailyPrayerService;

    // ✅ Create a new daily prayer
    @PostMapping("/create")
    public ResponseEntity<?> saveDailyPrayer(@RequestBody DailyPrayer dailyPrayer) {
        try {
            Optional<User> userDetails = dailyPrayerService.getUserDetails(dailyPrayer.getUsername());

            if (userDetails.isEmpty()) {
                System.out.println("user not found");
                return ResponseEntity.ok(new ApiResponse(false, "User not found"));
            }

            User user = userDetails.get();
            dailyPrayer.setRole(user.getRole());
            dailyPrayer.setPhone(user.getPhone());
            dailyPrayer.setAuthor(user.getFirst_name() + " " + user.getLast_name());

            // Use user's profile image if not provided
            dailyPrayer.setImagePath(
                (dailyPrayer.getImagePath() == null || dailyPrayer.getImagePath().isEmpty())
                    ? user.getImagePath()
                    : dailyPrayer.getImagePath()
            );

            dailyPrayerService.saveDailyPrayer(dailyPrayer);
            return ResponseEntity.ok(dailyPrayer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Error creating daily prayer: " + e.getMessage()));
        }
    }

    // ✅ Get all daily prayers
    @GetMapping("/all")
    public ResponseEntity<?> getDailyPrayers() {
        try {
            List<DailyPrayer> prayers = dailyPrayerService.getAllDailyPrayers();
            return ResponseEntity.ok(prayers);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Error fetching prayers: " + e.getMessage()));
        }
    }

    // ✅ Get specific daily prayer by ID
    @GetMapping("/specific/{prayerId}")
    public ResponseEntity<?> getDailyPrayer(@PathVariable Long prayerId) {
        try {
            Optional<DailyPrayer> prayer = dailyPrayerService.getDailyPrayer(prayerId);

            if (prayer.isEmpty()) {
                return ResponseEntity.ok(new ApiResponse(false, "Prayer not found"));
            }

            return ResponseEntity.ok(prayer.get());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Error fetching prayer: " + e.getMessage()));
        }
    }

    // ✅ Update existing daily prayer
    @PutMapping("/update/{prayerId}")
    public ResponseEntity<ApiResponse> updateDailyPrayer(@RequestBody DailyPrayer dailyPrayer, @PathVariable Long prayerId) {
        try {
            dailyPrayerService.updateDailyPrayer(dailyPrayer, prayerId);
            return ResponseEntity.ok(new ApiResponse(true, "Daily prayer updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Error updating prayer: " + e.getMessage()));
        }
    }

    // ✅ Delete specific prayer
    @DeleteMapping("/delete/{prayerId}")
    public ResponseEntity<ApiResponse> deletePrayer(@PathVariable Long prayerId) {
        try {
            String result = dailyPrayerService.deletePrayer(prayerId);
            return ResponseEntity.ok(new ApiResponse(true, result));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Error deleting prayer: " + e.getMessage()));
        }
    }
}
