package com.example.demo.api.dto.filme;

import com.example.demo.api.dto.ator.AtorResponseDTO;
import com.example.demo.api.dto.avaliacao.AvaliacaoResponseDTO;
import com.example.demo.api.dto.comentario.ComentarioResponseDTO;
import com.example.demo.domain.enums.Genero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmeResponseDTO {

    private Long idFilme;
    private String titulo;
    private String diretor;
    private Genero genero;
    private String sinopse;
    private Date lancamento;
    private int duracao;
    private int classificacao;
    private String distribuidora;

    // Relacionamentos
    private List<AvaliacaoResponseDTO> avaliacoes;
    private List<ComentarioResponseDTO> comentarios;
    private List<AtorResponseDTO> atores;

}
