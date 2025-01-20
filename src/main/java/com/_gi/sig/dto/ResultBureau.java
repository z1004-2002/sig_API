package com._gi.sig.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultBureau {
    private String Region;
    private String departement;
    private String arrondissement;
    private String matricule;
    private Integer totalElecteur;
    private List<ResultCandidat> resultCandidat;
}
