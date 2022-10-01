package com.edu.ctu.thesis.audit;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AuditListener {

    @PrePersist
    private void beforeAnyUpdate(AuditInterface audit) {
        LocalDateTime now = LocalDateTime.now();
        audit.setCreationTime(now);
        audit.setModificationTime(now);
    }

    @PreUpdate
    private void afterAnyUpdate(AuditInterface audit) {
        LocalDateTime now = LocalDateTime.now();
        audit.setModificationTime(now);
    }

    // @PrePersist
    // private void beforeAnyUpdate(Audi trainuoi) {
    // Audit audit = new Audit();
    // LocalDateTime now = LocalDateTime.now();
    // User user = trainuoi.getUser();

    // audit.setCreationUser(user.getUsername());
    // audit.setCreationTime(now);
    // audit.setModificationUser(user.getUsername());
    // audit.setModificationTime(now);

    // trainuoi.setAudit(audit);

    // log.info("Creation user [{}] - Creation time [{}].",
    // trainuoi.getAudit().getCreationUser(),
    // trainuoi.getAudit().getCreationTime());
    // }

    // @PrePersist
    // private void beforeAnyUpdate(User user) {
    // Audit audit = new Audit();
    // LocalDateTime now = LocalDateTime.now();

    // audit.setCreationUser(user.getUsername());
    // audit.setCreationTime(now);
    // audit.setModificationUser(user.getUsername());
    // audit.setModificationTime(now);

    // user.setAudit(audit);

    // log.info("Creation user [{}] - Creation time [{}].",
    // user.getAudit().getCreationUser(),
    // user.getAudit().getCreationTime());
    // }

    // @PreUpdate
    // private void afterAnyUpdate(TraiNuoi trainuoi) {
    // Audit audit = trainuoi.getAudit();
    // if(audit == null) {
    // audit = new Audit();
    // }

    // LocalDateTime now = LocalDateTime.now();
    // User user = trainuoi.getUser();

    // audit.setModificationUser(user.getUsername());
    // audit.setModificationTime(now);

    // trainuoi.setAudit(audit);

    // log.info("Modification user [{}] - Modification time [{}].",
    // trainuoi.getAudit().getCreationUser(),
    // trainuoi.getAudit().getCreationTime());
    // }

    // @PreUpdate
    // private void afterAnyUpdate(User user) {
    // Audit audit = user.getAudit();
    // if(audit == null) {
    // audit = new Audit();
    // }

    // LocalDateTime now = LocalDateTime.now();
    // audit.setModificationUser(user.getUsername());
    // audit.setModificationTime(now);

    // user.setAudit(audit);

    // log.info("Modification user [{}] - Modification time [{}].",
    // user.getAudit().getCreationUser(),
    // user.getAudit().getCreationTime());
    // }

}
