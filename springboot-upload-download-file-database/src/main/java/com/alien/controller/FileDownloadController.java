package com.alien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alien.model.DatabaseFile;
import com.alien.service.DatabaseFileService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class FileDownloadController {

	@Autowired
	private DatabaseFileService fileStorageService;

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

		DatabaseFile databaseFile = fileStorageService.getFile(fileName);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(databaseFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
				.body(new ByteArrayResource(databaseFile.getData()));
	}

}
