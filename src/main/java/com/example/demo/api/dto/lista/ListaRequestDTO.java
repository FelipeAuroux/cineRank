package com.example.demo.api.dto.lista;

import com.example.demo.api.dto.filme.FilmeRequestDTO;
import com.example.demo.api.dto.filme.FilmeResponseDTO;
import com.example.demo.api.dto.usuario.UsuarioRequestDTO;
import com.example.demo.domain.model.Filme;
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
public class ListaRequestDTO {

    private Date adicao = new Date();

    // Relacionamentos
    private UsuarioRequestDTO usuario;
    private List<FilmeRequestDTO> filmes;
}
