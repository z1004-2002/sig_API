package com._gi.sig.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(path = "/api/v1")
@CrossOrigin("*")
@Tag(name = "Verification")
public class VerifController {
    @GetMapping()
    public String verif() {
        return "Success";
    }
    
}
