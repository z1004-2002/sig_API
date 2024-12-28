package com._gi.sig.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bureau {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String matricule;
    private String region;
    private String departement;
    private String arrondisssement;
    private Integer nbreElecteurs;
    private UUID scrutateurId;
}
