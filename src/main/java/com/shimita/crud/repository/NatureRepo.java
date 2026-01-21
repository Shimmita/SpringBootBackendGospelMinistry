package com.shimita.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shimita.crud.model.NatureTalent;

public interface NatureRepo extends JpaRepository<NatureTalent,Long> {
    
}
