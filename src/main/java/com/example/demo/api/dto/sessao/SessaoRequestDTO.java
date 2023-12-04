package com.example.demo.api.dto.sessao;

import com.example.demo.domain.model.Cinema;
import com.example.demo.domain.model.Filme;
import com.example.demo.utils.RespostaDeAtributoPersonalizada;
import jakarta.validation.constraints.NotNull;
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
public class SessaoRequestDTO {

    private Long idSessao;
    @NotNull(message = RespostaDeAtributoPersonalizada.SECAO_ATR_HORARIO_VAZIO)
    private Date horario;
    @NotNull(message = RespostaDeAtributoPersonalizada.SECAO_ATR_INGRESSOS_VAZIO)
    private int ingressos;
    @NotNull(message = RespostaDeAtributoPersonalizada.SECAO_ATR_INGRESSOS_VAZIO)
    private int sala;
    @NotNull(message = RespostaDeAtributoPersonalizada.SECAO_ATR_CODIGO_VAZIO)
    private int codSessao;

    // Relacionamentos

    private List<Filme> filmes;
    private List<Cinema> cinemas;
}
