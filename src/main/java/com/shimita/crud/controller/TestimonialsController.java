package com.shimita.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shimita.crud.model.Testimonial;
import com.shimita.crud.service.TestimonialService;



@RestController
@RequestMapping("/api/v1/testimonials")
public class TestimonialsController {
    @Autowired
    TestimonialService testimonialService;

    // ✅ create testimonial
    @PostMapping("/create")
    public ResponseEntity<?> createTestimonial(@RequestBody Testimonial testimonial) {

        try {
            testimonialService.saveTestimonial(testimonial);
            return ResponseEntity.ok(testimonial);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Failed to save testimonial: " + e.getMessage()));
        }
    }

    // ✅ Get all testimonials
    @GetMapping("/all")
    public ResponseEntity<?> getAllTestimonials() {
        try {
            List<Testimonial> testimonials = testimonialService.getAllTestimonials();
            return ResponseEntity.ok(testimonials);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse(false, "Error fetching testimonials: " + e.getMessage()));
        }
    }

}
