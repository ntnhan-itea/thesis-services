package com.edu.ctu.thesis.file;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@RequestMapping(path = "api/files")
@Log4j2
public class FileResource {

    @Autowired
    FileService fileService;

    // Uploading a file
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            String filePathUri = this.fileService.uploadFie(file);
            log.info("Uploaded [{}] file successfully", filePathUri);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Uploaded [" + file.getOriginalFilename() + "] file Successfully");
        } catch (Exception e) {
            log.error("Cannot upload file: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    // Getting list of filenames that have been uploaded
    @GetMapping
    public ResponseEntity<?> getFiles() {
        try {
            List<String> files = Arrays.asList(this.fileService.getFiles());
            return ResponseEntity.status(HttpStatus.OK).body(files);
        } catch (Exception e) {
            log.error("Cannot get list files: ", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // Downloading a file
    @GetMapping(path = "/download/{path:.+}", produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public void downloadFile(@PathVariable("path") String fileName, HttpServletResponse response)
            throws IllegalStateException, IOException {

        // Creating a new InputStreamResource object
        Resource resource = this.fileService.downloadFile(fileName);
        StreamUtils.copy(resource.getInputStream(), response.getOutputStream());
    }

    // Downloading a file
    @GetMapping(path = "/download", produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    public ResponseEntity<?> downloadFileToByte(@RequestParam("name") String filename)
            throws IOException {

        Resource resource = this.fileService.downloadFile(filename);

        // Setting up value headerValue
        String imageName = StringUtils.isBlank(resource.getFilename()) ? "image.jpg" : resource.getFilename();
        String headerValue = "attachment; filename=\"" + imageName + "\"";

        // String encodedString = Base64.getEncoder().encodeToString(resource.getInputStream().readAllBytes());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                // .body(encodedString);
                .body(resource);
    }

}
