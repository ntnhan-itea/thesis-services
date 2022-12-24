package com.edu.ctu.thesis.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
@Getter
public class PropertyHelper {
    
    @Value("${folder.image.path}")
    private String imageFolderPath;

}
