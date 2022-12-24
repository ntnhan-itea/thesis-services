package com.edu.ctu.thesis.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.edu.ctu.thesis.util.PropertyHelper;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class FileService {

    @Autowired
    PropertyHelper propertyHelper;

    public String uploadFie(MultipartFile file) throws IOException {
        FileOutputStream fout = null;
        String filePath = null;
        try {
            filePath = this.convertToUploadUri(file);
            this.checkNotExistFile(filePath);

            fout = new FileOutputStream(filePath);
            fout.write(file.getBytes());
        } finally {
            if (fout != null) {
                fout.close();
            }
        }
        return filePath;
    }

    public String[] getFiles() {
        String folderPath = this.getUploadUrl();
        File directory = new File(folderPath);
        String[] filenames = directory.list();
        return filenames;
    }

    public Resource downloadFile(String filename) throws FileNotFoundException, MalformedURLException {
        // Checking whether the file requested for download exists or not
        String[] filenames = this.getFiles();
        boolean contains = Arrays.asList(filenames).contains(filename);
        if (!contains) {
            throw new FileNotFoundException("FIle Not Found");
        }

        // Setting up the filepath
        // String filePath = this.getUploadUrl() + File.separator + filename;
        Path filePath = Path.of(this.getUploadUrl(), filename);
        Resource resource = new UrlResource(filePath.toUri());

        return resource;
    }

    private String convertToUploadUri(MultipartFile file) {
        // Setting up the path of the file
        String fileUri = this.getUploadUrl() + File.separator
                + file.getOriginalFilename();

        return fileUri;
    }

    private void checkNotExistFile(String uriFilePath) throws IOException {
        File convertFile = new File(uriFilePath);
        String fileName = convertFile.getName();
        if (convertFile.exists()) {
            throw new IOException( fileName + " file is already exist");
        }
    }

    public String getUploadUrl() {
        return this.propertyHelper.getImageFolderPath() + "/uploads";
    }
}
