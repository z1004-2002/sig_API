package com._gi.sig.models;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Image {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private String path;
    private Long size;
	private String fileType;
    private String idBureau;
}
