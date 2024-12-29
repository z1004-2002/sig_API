package com._gi.sig.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com._gi.sig.models.Result;

@Repository
public interface ResultatRepository extends JpaRepository<Result, UUID>{
    @Query("SELECT r FROM Result r WHERE r.bureauId = ?1 AND r.candidatId = ?2")
    public List<Result> findByBureauIdAndCandidatId(UUID bureauId,UUID candidateId);

    @Query("SELECT r FROM Result r WHERE r.bureauId = ?1")
    public List<Result> findByBureauId(UUID bureauId);
}
