package com.edu.ctu.thesis.seafood.aonuoi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value = "api/seafood/ao-nuoi")
@CrossOrigin
@Log4j2
public class AoNuoiResource {
    
    @Autowired
    AoNuoiService aoNuoiService;
}
