package com._gi.sig.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com._gi.sig.models.Candidat;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat,UUID>{
    
}
