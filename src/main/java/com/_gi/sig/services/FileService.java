package com._gi.sig.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com._gi.sig.config.FileStorageProperties;
import com._gi.sig.models.FileDto;
import com._gi.sig.repository.BureauRepository;
import com._gi.sig.repository.FileRepository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@NoArgsConstructor
public class FileService {
	private FileRepository fileRepository;
	private Path fileStorageLocation;
	private BureauRepository bureauRepository;
	@Autowired
	public FileService(
			FileStorageProperties fileStorageProperties,
			BureauRepository bureauRepository,
			FileRepository fileRepository) {
		super();
		this.fileStorageLocation = Paths
				.get(System.getProperty("user.dir") + fileStorageProperties.getImage()).toAbsolutePath()
				.normalize();
		this.bureauRepository = bureauRepository;
		this.fileRepository = fileRepository;
		log.info("========>Image Path = {}<========", fileStorageLocation);

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new RuntimeException("Could not create the directory to upload.");
		}
	}

	public FileDto submitImage(MultipartFile file, UUID bureauId) {

		List<FileDto> files = fileRepository.findByIdBureau(bureauId);
		if (files.size()>0) {
			for (FileDto f : files){
				deleteFile(f.getId());
			}
		}

		// GENERATION OF VARCHAR

		String completeName = "sig_";
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "abcdefghijklmnopqrstuvxyz";

		StringBuilder s = new StringBuilder(50);
		for (int i = 0; i < 10; i++) {
			int index = (int) (str.length() * Math.random());
			s.append(str.charAt(index));
		}
		completeName += String.valueOf(s);

		// END OF GENERATION

		log.info("Image Name = {}", file.getOriginalFilename());
		String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
		try {
			Path targetLocation = this.fileStorageLocation.resolve(completeName + fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return fileRepository.save(
					new FileDto(null, completeName + fileName, fileName, file.getSize(),
							file.getContentType(), bureauId));
		} catch (IOException e) {
			throw new RuntimeException("Could not store file " + completeName + fileName + ". Please try again!", e);
		}
	}

	public FileDto getFileById(Long id) {
		return fileRepository.findById(id).orElse(null);
	}

	public void deleteFile(Long id) {
		FileDto fileDto = fileRepository.findById(id).orElse(null);
		if (fileDto != null) {
			Path filePath = this.fileStorageLocation.resolve(fileDto.getRealName());
			try {
				Files.deleteIfExists(filePath);
			} catch (IOException e) {
				log.error("Could not delete file: {}", filePath, e);
			}
			fileRepository.deleteById(id);
		}
	}

	public List<FileDto> getFiles() {
		return fileRepository.findAll();
	}

	public Resource loadImage(String fileName) {
		log.info("Load File = {} Successfully", fileName);
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new RuntimeException("Le fichier: " + fileName + " est introuvable");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Le fichier: " + fileName + " est introuvable");
		}
	}

	public List<FileDto> getFilesByBureauId(UUID idBureau) {
		if (!bureauRepository.existsById(idBureau)) {
			throw new IllegalArgumentException("bureau non existant");
		}
		return fileRepository.findByIdBureau(idBureau);
	}
}
