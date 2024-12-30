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

import com._gi.sig.dto.BureauDto;
import com._gi.sig.dto.BureauRequest;
import com._gi.sig.services.BureauService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/bureau")
@CrossOrigin("*")
@Tag(name = "Bureau")
public class BureauController {
    @Autowired
    private BureauService bureauService;

    @PostMapping("/create")
    public BureauDto createBureau(@RequestBody BureauRequest request) {
        return bureauService.createBureau(request);
    }

    @GetMapping("/all")
    public List<BureauDto> getBureaus(){
        return bureauService.getBureaus();
    }

    @GetMapping("/one/{id}")
    public BureauDto getBureau(@PathVariable UUID id){
        return bureauService.getBureau(id);
    }   
    @GetMapping("/scrutatreur/{id}")
    public BureauDto getBureauByScrutateur(@PathVariable UUID id){
        return bureauService.getBureauByScrutateur(id);
    }
 
    @PutMapping("/update/{id}")
    public BureauDto updateBureau(@PathVariable UUID id, @RequestBody BureauRequest request){
        return bureauService.updateBureau(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBureau(@PathVariable UUID id) throws Exception{
        return bureauService.deleteBureau(id);
    }
}
