package com._gi.sig.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._gi.sig.models.Candidat;
import com._gi.sig.services.CandidatService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/candidat")
@CrossOrigin("*")
@Tag(name = "Candidat")
public class CandidatController {
    @Autowired
    private CandidatService candidatService;

    @PostMapping("/create")
    public Candidat createCandidat(@RequestBody Candidat candidat) {
        return candidatService.create(candidat);
    }

    @GetMapping("/{id}")
    public Candidat getCandidat(@PathVariable UUID id) {
        return candidatService.getCandidat(id);
    }

    @GetMapping("/all")
    public List<Candidat> getAllCandidat() {
        return candidatService.getAllCandidat();
    }

    @PutMapping("/update/{id}")
    public Candidat updateCandidat(@PathVariable UUID id, @RequestBody Candidat candidat) {
        return candidatService.updateCandidat(id, candidat);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCandidat(@PathVariable UUID id) {
        return candidatService.deleteCandidat(id);
    }
}
