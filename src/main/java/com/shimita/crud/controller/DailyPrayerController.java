package com.shimita.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shimita.crud.model.DailyPrayer;
import com.shimita.crud.model.User;
import com.shimita.crud.service.DailyPrayerService;

@RestController
@RequestMapping("api/v1/daily")
public class DailyPrayerController {
    @Autowired
    DailyPrayerService dailyPrayerService;

    // save daily prayer
    @PostMapping("create")
    public DailyPrayer saveDailyPrayer(@RequestBody DailyPrayer dailyPrayer) {

        Optional<User> userDetails = dailyPrayerService.getUserDetails(dailyPrayer.getUsername());
        dailyPrayer.setRole(userDetails.get().getRole());
        dailyPrayer.setPhone(userDetails.get().getPhone());
        dailyPrayer.setAuthor(userDetails.get().getFirst_name() + " " + userDetails.get().getLast_name());
        dailyPrayer.setImagePath(
            (dailyPrayer.getImagePath()==null || dailyPrayer.getImagePath().isEmpty())
            ? userDetails.get().getImagePath(): dailyPrayer.getImagePath());

        return dailyPrayerService.saveDailyPrayer(dailyPrayer);
    }

    // get all daily prayer
    @GetMapping("all")
    public List<DailyPrayer> getDailyPrayers() {
        return dailyPrayerService.getAllDailyPrayers();
    }

    // get specific daily prayer
    @GetMapping("{prayerId}")
    public Optional<DailyPrayer> getDailyPrayer(@PathVariable Long prayerId) {
        return dailyPrayerService.getDailyPrayer(prayerId);
    }

    // update specific prayer
    @PutMapping("{prayerId}")
    public DailyPrayer updateDailyPrayer(@RequestBody DailyPrayer dailyPrayer, @PathVariable Long prayerId) {
        return dailyPrayerService.updateDailyPrayer(dailyPrayer, prayerId);
    }

    // delete specific prayer
    @DeleteMapping("{prayerId}")
    public String deletePrayer(@PathVariable Long prayerId) {
        return dailyPrayerService.deletePrayer(prayerId);
    }

}
