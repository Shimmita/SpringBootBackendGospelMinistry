package com.shimita.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shimita.crud.model.WeeklyPrayer;

public interface WeeklyRepo extends JpaRepository<WeeklyPrayer,Long> {
    
}
