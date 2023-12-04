package com.example.demo.api.dto.avaliacao;

import com.example.demo.api.dto.usuario.UsuarioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoResponseDTO {

    private Long idAvaliacao;
    private int pontuacao;
    private Date dataHora;

    // Relacionamentos
    private UsuarioResponseDTO usuario;
}
