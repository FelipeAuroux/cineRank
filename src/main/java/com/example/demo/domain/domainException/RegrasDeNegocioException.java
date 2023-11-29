package com.example.demo.domain.domainException;

public class RegrasDeNegocioException extends RuntimeException{
    public RegrasDeNegocioException(String mensagem){
        super(mensagem);
    }
}
