package com.edu.ctu.thesis.seafood.vungnuoi;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoi;
import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoiService;
import com.edu.ctu.thesis.seafood.user.User;

@Service
public class VungNuoiService {

    @Autowired
    VungNuoiRepository vungNuoiRepository;

    @Autowired
    TraiNuoiService traiNuoiService;

    public VungNuoi createVungNuoi(VungNuoi vungNuoi) {
        User user = vungNuoi.getUser();
        TraiNuoi traiNuoiInDB = this.traiNuoiService.getTraiNuoi(user);
        String tenVungNuoi = this.checkVungNuoiIsNotExistInDB(vungNuoi);
        vungNuoi.setTraiNuoi(traiNuoiInDB);
        vungNuoi.setTenVungNuoi(tenVungNuoi);

        return this.vungNuoiRepository.save(vungNuoi);
    }

    public String checkVungNuoiIsNotExistInDB(VungNuoi vungNuoi) {
        String tenVungNuoi = vungNuoi.getTenVungNuoi();
        VungNuoi vungNuoiInDB = this.getVungNuoiByTenVungNuoi(tenVungNuoi);
        if(vungNuoiInDB != null) {
            throw new IllegalArgumentException("Vung nuoi [" + tenVungNuoi.trim() + "] da ton tai trong DB!");
        }
        return tenVungNuoi.trim();
    }

    public VungNuoi getVungNuoiByTenVungNuoi(String tenVungNuoi) {
        if(StringUtils.isBlank(tenVungNuoi)) {
            throw new IllegalArgumentException("Invalid ten vung nuoi input!");
        }

        return this.vungNuoiRepository.findByTenVungNuoi(tenVungNuoi.trim());
    }


}
