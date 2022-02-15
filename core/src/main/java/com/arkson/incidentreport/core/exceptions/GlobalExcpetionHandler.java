package com.arkson.incidentreport.core.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExcpetionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalExeption(Exception e) {
        log.error("Global Error={}", e.getMessage(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        log.error("Not Found Error={}", e.getMessage(), e);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> handleInternalServerErrorException(InternalServerErrorException e) {
        log.error("Internal Server Error={}", e.getMessage(), e);
        return ResponseEntity.internalServerError().build();
    }

}
