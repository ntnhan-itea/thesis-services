package com.edu.ctu.thesis.email;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Log4j2
public class EmailEntity {

    @Email
    @NotBlank(message = "Receiver should not be missing")
    private String to;

    private String cc;

    private String bcc;

    @NotBlank(message = "Subject should not be missing")
    private String subject;

    @NotBlank(message = "Content body should not be missing")
    private String body;

    public boolean isValid() {
        boolean isValid = true;

        if (StringUtils.isBlank(this.to)) {
            log.error("Receiver is missing");
            isValid = false;
        }

        if(StringUtils.isBlank(this.subject)) {
            log.error("Subject is missing");
            isValid = false;
        }

        if(StringUtils.isBlank(this.body)) {
            log.error("Content email is missing");
            isValid = false;
        }

        return isValid;
    }
}
