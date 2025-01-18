package com._gi.sig.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "data")
@AllArgsConstructor
@NoArgsConstructor
public class FileStorageProperties {
	private String image;
}