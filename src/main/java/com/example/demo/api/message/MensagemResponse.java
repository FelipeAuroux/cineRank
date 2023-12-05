package com.example.demo.api.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensagemResponse {

    public MensagemResponse(String mensagem) {
        this.mensagem = mensagem;
    }

    private String mensagem;
    private Date data = new Date();
}
