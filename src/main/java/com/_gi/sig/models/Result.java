package com._gi.sig.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table
@Data
@Builder
public class Result {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private UUID bureauId;
    private UUID candidatId;
    private Integer nombreVoie;
}
