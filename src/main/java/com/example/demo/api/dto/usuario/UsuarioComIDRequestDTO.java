package com.example.demo.api.dto.usuario;

import com.example.demo.domain.enums.Roles;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioComIDRequestDTO {

    @NotNull
    private Long idUsuario;
    @NotBlank
    private String nome;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 6, max = 20)
    private String usuario;
    @NotBlank
    @Size(min = 8)
    private String senha;
    @NotNull
    private Date dataRegistro = new Date();
    @NotNull
    private Date dataNascimento;
    @CPF
    @NotBlank
    private String cpf;
    @Enumerated
    @NotNull
    private Roles role = Roles.ROLE_USER;
}
