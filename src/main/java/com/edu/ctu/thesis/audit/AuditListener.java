package com.edu.ctu.thesis.audit;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.edu.ctu.thesis.seafood.user.User;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AuditListener {

    @PrePersist
    private void beforeAnyUpdate(User user) {
        Audit audit = new Audit();
        LocalDateTime now = LocalDateTime.now();
        audit.setCreationUser(user.getUsername());
        audit.setCreationTime(now);
        audit.setModificationUser(user.getUsername());
        audit.setModificationTime(now);
        user.setAudit(audit);

        log.info("Creation user [{}] - Creation time [{}].", user.getAudit().getCreationUser(),
                user.getAudit().getCreationTime());
    }

    @PreUpdate
    private void afterAnyUpdate(User user) {
        Audit audit = new Audit();
        LocalDateTime now = LocalDateTime.now();
        audit.setModificationUser(user.getUsername());
        audit.setModificationTime(now);
        user.setAudit(audit);

        log.info("Modification user [{}] - Modification time [{}].", user.getAudit().getCreationUser(),
                user.getAudit().getCreationTime());
    }

}
