package com.shimita.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shimita.crud.model.NatureTalent;
import com.shimita.crud.model.SundayDiary;
import com.shimita.crud.service.NatureService;
import com.shimita.crud.service.SundayDiaryService;

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
@RequestMapping("/api/v1/sunday")
public class SundayController {
    @Autowired
    SundayDiaryService sundayDiaryService;

    // ✅ create
    @PostMapping("/create")
    public ResponseEntity<?> createSundayDiary(@RequestBody SundayDiary sundayDiary) {

        try {
            sundayDiaryService.saveSundayDiary(sundayDiary);
            return ResponseEntity.ok(sundayDiary);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Failed to save sunday diary: " + e.getMessage()));
        }
    }

    // ✅ Get all 
    @GetMapping("/all")
    public ResponseEntity<?> getAllDiaries() {
        try {
            List<SundayDiary> sundayDiaries = sundayDiaryService.getAlSundayDiaries();
            return ResponseEntity.ok(sundayDiaries);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Error fetching sunday diaries: " + e.getMessage()));
        }
    }
}
