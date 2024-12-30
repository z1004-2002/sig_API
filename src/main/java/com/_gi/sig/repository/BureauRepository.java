package com._gi.sig.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com._gi.sig.models.Bureau;

public interface BureauRepository extends JpaRepository<Bureau, UUID>{
    @Query("SELECT b FROM Bureau b WHERE b.scrutateurId = ?1")
    public List<Bureau> findByScrutateur(UUID scrutateurId);
}
