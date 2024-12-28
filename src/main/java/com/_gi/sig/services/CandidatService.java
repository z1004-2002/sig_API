package com._gi.sig.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._gi.sig.models.Candidat;
import com._gi.sig.repository.CandidatRepository;

@Service
public class CandidatService {
    @Autowired
    private CandidatRepository candidatRepository;

    public Candidat create(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    public Candidat getCandidat(UUID id) {
        return candidatRepository.findById(id).orElse(null);
    }

    public List<Candidat> getAllCandidat() {
        return candidatRepository.findAll();
    }

    public Candidat updateCandidat(UUID id, Candidat candidat) {
        Candidat c = candidatRepository.findById(id).get();
        if (c == null) {
            throw new IllegalStateException("Candidat not found");
        }
        c.setCouleur(candidat.getCouleur());
        c.setFirstName(candidat.getFirstName());
        c.setLastName(candidat.getLastName());
        c.setParti(candidat.getParti());
        c.setId(candidat.getId());
        return candidatRepository.save(c);
    }

    public String deleteCandidat(UUID id) {
        Candidat c = candidatRepository.findById(id).get();
        if (c == null) {
            throw new IllegalStateException("Candidat not found");
        }
        candidatRepository.deleteById(id);
        return "Candidat with id " + id.toString() + " is deleted";
    }
}
