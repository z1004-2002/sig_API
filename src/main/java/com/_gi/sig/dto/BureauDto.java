package com._gi.sig.dto;

import java.util.List;
import java.util.UUID;

import com._gi.sig.models.FileDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BureauDto {
    private UUID id;
    private String matricule;
    private String region;
    private String departement;
    private String arrondisssement;
    private Integer nbreElecteurs;
    private UserDto scrutateur;
    private List<FileDto> pv;
}
