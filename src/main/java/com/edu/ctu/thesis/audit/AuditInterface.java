package com.edu.ctu.thesis.audit;

import java.time.LocalDateTime;

public interface AuditInterface {
    
    void setCreationUser(String creationUser);
    void setCreationTime(LocalDateTime creationTime);

    void setModificationUser(String modificationUser);
    void setModificationTime(LocalDateTime modificationTime);

}
