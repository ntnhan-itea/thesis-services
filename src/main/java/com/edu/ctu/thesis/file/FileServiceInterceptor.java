package com.edu.ctu.thesis.file;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Log4j2
public class FileServiceInterceptor {


    @Before("execution(* com.edu.ctu.thesis.file.FileService.*(..))")
    public void before(JoinPoint joinPoint){
        log.info(" before called methodeeee: " + joinPoint.toString());
        log.info(" before called methodeeee toShortString: " + joinPoint.toShortString());
        log.info(" before called methodeeee getKind: " + joinPoint.getKind());
        log.info(" before called methodeeee getSignature: " + joinPoint.getSignature());
        log.info(" before called methodeeee getSourceLocation: " + joinPoint.getSourceLocation());
        // joinPoint.
    }
    // @Before("execution(* com.edu.ctu.thesis.file.*.*(..))") 
    // public void before(JoinPoint joinPoint){
    //     log.info(" before called methodeeee: " + joinPoint.toString());
    // }

    @After("execution(* com.edu.ctu.thesis.file.*.*(..))")
    public void after(JoinPoint joinPoint){
        log.info(" after called methodeeee: " + joinPoint.toString());
    }

}
