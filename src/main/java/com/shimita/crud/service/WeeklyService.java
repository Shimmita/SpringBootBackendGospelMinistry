package com.shimita.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimita.crud.model.WeeklyPrayer;
import com.shimita.crud.repository.UserRepository;
import com.shimita.crud.repository.WeeklyRepo;

@Service
public class WeeklyService {
    @Autowired
    WeeklyRepo weeklyRepo;
    @Autowired
    UserRepository userRepository;

    // save prayer
    public WeeklyPrayer saveWeeklyPrayer(WeeklyPrayer weeklyPrayer) {
        return weeklyRepo.save(weeklyPrayer);
    }

    // get all daily prayers
    public List<WeeklyPrayer> getAllWeeklyPrayers() {
        return weeklyRepo.findAll();
    }

}
