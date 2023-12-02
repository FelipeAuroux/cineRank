package com.example.demo.api.dto.ator;

import com.example.demo.api.dto.filme.FilmeResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtorResponseDTO {
    private Long idAtor;
    private String nome;
    private Date dataNascimento;
    private List<FilmeResponseDTO> filmes;
}
