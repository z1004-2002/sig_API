package com._gi.sig.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com._gi.sig.models.Bureau;

public interface BureauRepository extends JpaRepository<Bureau, UUID>{
    
}
