package com.example.demo.api.dto.login;

import com.example.demo.utils.RespostaDeAtributoPersonalizada;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = RespostaDeAtributoPersonalizada.LOGIN_ATR_USUARIO_VAZIO)
    @Size(min = 6, max = 20, message = RespostaDeAtributoPersonalizada.LOGIN_ATR_USUARIO_TAMANHO)
    private String usuario;
    @NotBlank(message = RespostaDeAtributoPersonalizada.LOGIN_ATR_SENHA_VAZIO)
    @Size(min = 8, message = RespostaDeAtributoPersonalizada.LOGIN_ATR_SENHA_TAMANHO)
    private String senha;
}
