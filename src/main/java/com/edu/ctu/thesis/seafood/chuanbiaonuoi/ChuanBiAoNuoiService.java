package com.edu.ctu.thesis.seafood.chuanbiaonuoi;

import org.springframework.stereotype.Service;

@Service
public class ChuanBiAoNuoiService {

    private final ChuanBiAoNuoiRepository chuanBiAoNuoiRepository;

    public ChuanBiAoNuoiService(ChuanBiAoNuoiRepository chuanBiAoNuoiRepository) {
        this.chuanBiAoNuoiRepository = chuanBiAoNuoiRepository;
    }

    public ChuanBiAoNuoi createChuanBiAoNuoi(ChuanBiAoNuoi chuanBiAoNuoi) {
        return this.chuanBiAoNuoiRepository.save(chuanBiAoNuoi);
    }

}
