package com.example.demo.domain.domainException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class DomainExceptionHandler {
    @ExceptionHandler(RegrasDeNegocioException.class)
    public ResponseEntity<Problema> handleRegrasDeNegocioException(RegrasDeNegocioException ex) {

        var status = HttpStatus.BAD_REQUEST;

        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(OffsetDateTime.now());

        return ResponseEntity.badRequest().body(problema);
    }
}
