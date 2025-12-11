package com.shimita.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimita.crud.model.Testimonial;
import com.shimita.crud.repository.TestimonialRepo;

@Service
public class TestimonialService {
    @Autowired
    private TestimonialRepo testimonialRepo;

    // save testimonial
    public Testimonial saveTestimonial(Testimonial testimonial) {
        if (testimonial.getUsername().isBlank() || testimonial.getRole().isBlank()
                || testimonial.getDetails().isBlank()) {
            return testimonialRepo.save(testimonial);
        } else
            return testimonial;

    }

    // get all testimonials
    public List<Testimonial> getAllTestimonials() {
        return testimonialRepo.findAll();
    }

    // delete testimonial by id
    public String deleteTestimonial(Long testmonialId) {
        testimonialRepo.deleteById(testmonialId);
        return "deleted successfully!";
    }
}
