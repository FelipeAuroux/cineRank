package com.example.demo.api.dto.lista;

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
public class ListaResponseDTO {

    private Long idLista;
    private Date adicao;

    // Relacionamentos
    private List<Filme> filmes;
}
