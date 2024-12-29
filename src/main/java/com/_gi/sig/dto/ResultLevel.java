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
public class ResultLevel {
    private String level;
    private String name;
    private Integer totalElecteur;
    private List<ResultCandidat> resultCandidat;
}
