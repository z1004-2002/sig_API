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
public class ResultCandidat {
    private UUID idCandidat;
    private String nomCandidat;
    private String parti;
    private String couleur;
    private Integer nombreVoie;
}
