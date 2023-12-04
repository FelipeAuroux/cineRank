package com.example.demo.api.dto.filme;

import com.example.demo.domain.enums.Genero;
import com.example.demo.domain.model.Usuario;
import com.example.demo.utils.RespostaDeAtributoPersonalizada;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmeRequestDTO {


    private Long idFilme;

    @NotBlank(message = RespostaDeAtributoPersonalizada.FILME_ATR_TITULO_VAZIO)
    private String titulo;

    @NotBlank(message = RespostaDeAtributoPersonalizada.FILME_ATR_DIRETOR_VAZIO)
    private String diretor;

    @Enumerated
    @NotNull(message = RespostaDeAtributoPersonalizada.FILME_ATR_GENERO_NULO)
    private Genero genero;

    @NotBlank(message = RespostaDeAtributoPersonalizada.FILME_ATR_SINOPSE_VAZIO)
    @Size(max = 280, message = RespostaDeAtributoPersonalizada.FILME_ATR_SINOPSE_TAMANHO)
    private String sinopse;

    private Date lancamento;

    @NotNull(message = RespostaDeAtributoPersonalizada.FILME_ATR_DURACAO_NULO)
    @Positive(message = RespostaDeAtributoPersonalizada.FILME_ATR_DURACAO_POSITIVO)
    private int duracao;

    @NotNull(message = RespostaDeAtributoPersonalizada.FILME_ATR_CLASSIFICACAO_NULO)
    private int classificacao;

    @NotBlank(message = RespostaDeAtributoPersonalizada.FILME_ATR_DISTRIBUIDORA_VAZIO)
    private String distribuidora;

    // Informar json aninhado de usuario com id para cadastrar filme

    @NotNull
    private Usuario usuario;

}
