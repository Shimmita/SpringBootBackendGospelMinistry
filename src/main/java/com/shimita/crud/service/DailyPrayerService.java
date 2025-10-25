package com.shimita.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimita.crud.model.DailyPrayer;
import com.shimita.crud.model.User;
import com.shimita.crud.repository.DailyPrayerRepo;
import com.shimita.crud.repository.UserRepository;

@Service
public class DailyPrayerService {
    @Autowired
    DailyPrayerRepo dailyPrayerRepo;
    @Autowired
    UserRepository userRepository;

    // save prayer
    public DailyPrayer saveDailyPrayer(DailyPrayer dailyPrayer) {
        return dailyPrayerRepo.save(dailyPrayer);
    }

    // get all daily prayers
    public List<DailyPrayer> getAllDailyPrayers(){
        return dailyPrayerRepo.findAll();
    }

    // get daily prayer by id
    public Optional<DailyPrayer> getDailyPrayer(Long prayerId) {
        return dailyPrayerRepo.findById(prayerId);
    }

    // get user details by username
    public Optional<User> getUserDetails(String username) {
        return userRepository.findByUsername(username);
    }

    // update daily prayer
    public DailyPrayer updateDailyPrayer(DailyPrayer dailyPrayer, Long prayerId) {
        DailyPrayer updateDailyPrayer = dailyPrayerRepo.getReferenceById(prayerId);
        updateDailyPrayer.setDetails(dailyPrayer.getDetails());
        updateDailyPrayer.setVerse(dailyPrayer.getVerse());
        updateDailyPrayer.setImagePath(dailyPrayer.getImagePath());
        updateDailyPrayer.setPhone(dailyPrayer.getPhone());
        updateDailyPrayer.setRole(dailyPrayer.getRole());
        return dailyPrayerRepo.save(updateDailyPrayer);

    }

    // delete prayer by id
    public String deletePrayer(Long prayerId) {
        dailyPrayerRepo.deleteById(prayerId);
        return "deleted successfully!";
    }

}
