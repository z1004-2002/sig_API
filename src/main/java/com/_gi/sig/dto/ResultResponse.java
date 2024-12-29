package com._gi.sig.dto;

import java.util.UUID;

import com._gi.sig.models.Candidat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse {
    private UUID id;
    private BureauDto bureau;
    private Candidat candidat;
    private Integer nombreVoie;
}
