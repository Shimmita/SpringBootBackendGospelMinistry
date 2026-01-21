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
import com.shimita.crud.model.Testimonial;
import com.shimita.crud.service.NatureService;
import com.shimita.crud.service.TestimonialService;

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
@RequestMapping("/api/v1/talent")
public class NatureTalentController {
    @Autowired
    NatureService natureService;

    // ✅ create 
    @PostMapping("/create")
    public ResponseEntity<?> createNatureTalent(@RequestBody NatureTalent natureTalent) {

        try {
            natureService.savNatureTalent(natureTalent);
            return ResponseEntity.ok(natureTalent);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Failed to save talent: " + e.getMessage()));
        }
    }

    // ✅ Get all 
    @GetMapping("/all")
    public ResponseEntity<?> getAllTalents() {
        try {
            List<NatureTalent> testimonials = natureService.getAllNatureTalents();
            return ResponseEntity.ok(testimonials);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Error fetching talent posts: " + e.getMessage()));
        }
    }
}
