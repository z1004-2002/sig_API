package com._gi.sig.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BureauRes {
    private UUID id;
    private String matricule;
    private String region;
    private String departement;
    private String arrondisssement;
    private Integer nbreElecteurs;
}
