package com._gi.sig.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com._gi.sig.models.FileDto;
import com._gi.sig.services.FileService;


import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "File")
@CrossOrigin("*")
@RequestMapping(path = "/api/v1/file", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping(path = "/bureau/{idBureau}")
	@Operation(summary = "Send new file")
	public FileDto submitImage(
			@RequestParam("file") MultipartFile file,
			@PathVariable UUID idBureau
	) {
		log.info("Controller save File ({})", file);
		return fileService.submitImage(file, idBureau);
	}
    @GetMapping("/download/{realName}/{fileName}")
	@Operation(summary = "Get File")
	public ResponseEntity<Resource> downloadProfileImage(
			@PathVariable String realName,
			@PathVariable String fileName,
			HttpServletRequest request
	) {
		Resource resource = fileService.loadImage(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			System.out.println("Could Not Determine file ");
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+realName+"\"")
				.contentType(MediaType.parseMediaType(contentType))
				.body(resource);
	}

    @GetMapping("/bureau/{idBureau}")
	@Operation(summary = "Get File")
    public List<FileDto> getByBureauId(@PathVariable("idBureau") UUID idBureau){
        return fileService.getFilesByBureauId(idBureau);
    }
    @GetMapping("/all")
    public List<FileDto> getAllFile() {
        return fileService.getFiles();
    }

    // delete file
    @DeleteMapping("/delete/{id}")
    public void deleteFile(@PathVariable Long id) {
        fileService.deleteFile(id);
    }
    
}
