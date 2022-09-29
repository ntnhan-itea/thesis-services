package com.edu.ctu.thesis.audit;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.edu.ctu.thesis.seafood.TraiNuoi.TraiNuoi;
import com.edu.ctu.thesis.seafood.user.User;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AuditListener {

    @PrePersist
    private void beforeAnyUpdate(TraiNuoi trainuoi) {
        Audit audit = new Audit();
        LocalDateTime now = LocalDateTime.now();
        User user = trainuoi.getUser();

        audit.setCreationUser(user.getUsername());
        audit.setCreationTime(now);
        audit.setModificationUser(user.getUsername());
        audit.setModificationTime(now);

        trainuoi.setAudit(audit);

        log.info("Creation user [{}] - Creation time [{}].", trainuoi.getAudit().getCreationUser(),
                trainuoi.getAudit().getCreationTime());
    }

    @PreUpdate
    private void afterAnyUpdate(TraiNuoi trainuoi) {
        Audit audit = new Audit();
        LocalDateTime now = LocalDateTime.now();
        User user = trainuoi.getUser();

        audit.setModificationUser(user.getUsername());
        audit.setModificationTime(now);

        trainuoi.setAudit(audit);

        log.info("Modification user [{}] - Modification time [{}].", trainuoi.getAudit().getCreationUser(),
                trainuoi.getAudit().getCreationTime());
    }

}
