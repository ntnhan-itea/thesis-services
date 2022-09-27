package com.edu.ctu.thesis.seafood.TraiNuoi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraiNuoiService {
    
    @Autowired
    TraiNuoiRepository traiNuoiRepository;

    public TraiNuoi createTraiNuoi(TraiNuoi traiNuoi) {
        return this.traiNuoiRepository.save(traiNuoi);
    }

}
