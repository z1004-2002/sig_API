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

import com._gi.sig.dto.ResultLevel;
import com._gi.sig.dto.ResultRequest;
import com._gi.sig.dto.ResultResponse;
import com._gi.sig.services.ResultService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/result")
@Tag(name = "Resultats", description = "Opérations sur les résultats des élections")
@CrossOrigin("*")
public class ResultController {
    @Autowired
    private ResultService resultService;

    @PostMapping("/add/bureau/{bureauId}/candidat/{candidatId}")
    public ResultResponse addResult(@RequestBody ResultRequest request,@PathVariable UUID bureauId,@PathVariable UUID candidatId){
        return resultService.addResult(request,bureauId,candidatId);
    }

    @GetMapping("/bureau/{bureauId}")
    public List<ResultResponse> getAllResultsByBureau(@PathVariable UUID bureauId){
        return resultService.getAllResultsByBureau(bureauId);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteResult(@PathVariable UUID id){
        resultService.deleteResult(id);
    }

    @GetMapping("/arrondissement")
    public List<ResultLevel> resultByArrondissement() {
        return resultService.resultByArrondissement();
    }
    @GetMapping("/departement")
    public List<ResultLevel> resultByDepartement() {
        return resultService.resultByDepartement();
    }
    @GetMapping("/region")
    public List<ResultLevel> resultByRegion() {
        return resultService.resultByRegion();
    }
    @GetMapping("/final")
    public ResultLevel finalResult() {
        return resultService.Allresult();
    }
    
}
