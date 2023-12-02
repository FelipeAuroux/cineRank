package com.example.demo.api.dto.cinema;

import com.example.demo.api.dto.sessao.SessaoResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CinemaResponseDTO {

    private Long idCinema;
    private String nome;
    private String endereco;
    private String funcionamento;
    private int salas;
    private int cnpj;

    // Relacionamentos
    private List<SessaoResponseDTO> sessoes;

}
