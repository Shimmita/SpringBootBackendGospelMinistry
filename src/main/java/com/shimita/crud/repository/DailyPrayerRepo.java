package com.shimita.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shimita.crud.model.DailyPrayer;

public interface DailyPrayerRepo extends JpaRepository<DailyPrayer,Long> {

    
} 
