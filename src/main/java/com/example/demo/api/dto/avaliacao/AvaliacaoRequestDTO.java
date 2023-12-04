package com.example.demo.api.dto.avaliacao;

import com.example.demo.api.dto.filme.FilmeRequestDTO;
import com.example.demo.api.dto.usuario.UsuarioRequestDTO;
import com.example.demo.utils.RespostaDeAtributoPersonalizada;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoRequestDTO {

    @NotNull (message = RespostaDeAtributoPersonalizada.AVALIACAO_ATR_PONTUACAO_VAZIO)
    private int pontuacao;
    private Date dataHora = new Date();

    // Relacionamentos
    private UsuarioRequestDTO usuario;
    private FilmeRequestDTO filme;

}
