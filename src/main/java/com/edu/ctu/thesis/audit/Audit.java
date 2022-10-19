package com.edu.ctu.thesis.audit;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Audit {
            
    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    @Column(name = "creation_user")
    private String creationUser;

    @Column(name = "modification_time", nullable = false)
    private LocalDateTime modificationTime;

    @Column(name = "modification_user")
    private String modificationUser;
}
