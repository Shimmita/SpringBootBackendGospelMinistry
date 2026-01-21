package com.shimita.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimita.crud.model.SundayDiary;
import com.shimita.crud.repository.SundayDiaryRepo;

@Service
public class SundayDiaryService {
    @Autowired
    private SundayDiaryRepo sundayRepo;

    // save
    public SundayDiary saveSundayDiary(SundayDiary sundayDiary) {
        if (!sundayDiary.getUsername().isBlank() || !sundayDiary.getRole().isBlank()
                || !sundayDiary.getDetails().isBlank()) {
            return sundayRepo.save(sundayDiary);
        } else
            return sundayDiary;

    }

    // get all
    public List<SundayDiary> getAlSundayDiaries() {
        return sundayRepo.findAll();
    }
}
