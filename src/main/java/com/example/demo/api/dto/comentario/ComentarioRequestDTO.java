package com.example.demo.api.dto.comentario;

import com.example.demo.domain.model.Filme;
import com.example.demo.domain.model.Usuario;
import com.example.demo.utils.RespostaDeAtributoPersonalizada;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ComentarioRequestDTO {

    @NotBlank (message = RespostaDeAtributoPersonalizada.COMENTARIO_ATR_TEXTO_VAZIO)
    @Size(max = 280)
    private String texto;
    @NotNull
    private Date dataHora = new Date();
    @NotNull
    private Filme filme;
    @NotNull
    private Usuario usuario;
}
