package com.shimita.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shimita.crud.model.SundayDiary;

public interface SundayDiaryRepo extends JpaRepository<SundayDiary,Long> {
    
}
