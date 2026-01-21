package com.shimita.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimita.crud.model.NatureTalent;
import com.shimita.crud.repository.NatureRepo;

@Service
public class NatureService {
    @Autowired
    private NatureRepo natureRepo;
    // save nature talent post
    public NatureTalent savNatureTalent(NatureTalent natureTalent) {
        if (!natureTalent.getUsername().isBlank() || !natureTalent.getRole().isBlank()
                || !natureTalent.getDetails().isBlank()) {
            return natureRepo.save(natureTalent);
        } else
            return natureTalent;

    }

    // get all nature talents posts
    public List<NatureTalent> getAllNatureTalents() {
        return natureRepo.findAll();
    }
}
