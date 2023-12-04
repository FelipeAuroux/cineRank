package com.example.demo.api.dto.cinema;

import com.example.demo.api.dto.sessao.SessaoRequestDTO;
import com.example.demo.api.dto.sessao.SessaoResponseDTO;
import com.example.demo.utils.RespostaDeAtributoPersonalizada;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CinemaRequestDTO {

    @NotBlank(message = RespostaDeAtributoPersonalizada.CINEMA_ATR_NOME_VAZIO)
    private String nome;
    @NotBlank(message = RespostaDeAtributoPersonalizada.CINEMA_ATR_ENDERECO_VAZIO)
    private String endereco;
    @NotBlank(message = RespostaDeAtributoPersonalizada.CINEMA_ATR_FUNCIONAMENTO_VAZIO)
    private String funcionamento;
    @NotNull(message = RespostaDeAtributoPersonalizada.CINEMA_ATR_SALAS_VAZIO)
    private int salas;
    @CNPJ(message = RespostaDeAtributoPersonalizada.CINEMA_ATR_CNPJ_VAZIO)

    private String cnpj;

    private List<SessaoRequestDTO> sessoes;
}
