package com._gi.sig.dto;

import java.util.UUID;

import com._gi.sig.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BureauRequest {
    private UUID id;
    private String matricule;
    private String region;
    private String departement;
    private String arrondisssement;
    private Integer nbreElecteurs;
    private User scrutateur;
}
