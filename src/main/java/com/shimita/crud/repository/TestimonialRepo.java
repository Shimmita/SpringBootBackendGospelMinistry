package com.shimita.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shimita.crud.model.Testimonial;

public interface TestimonialRepo extends JpaRepository<Testimonial,Long>{

}
