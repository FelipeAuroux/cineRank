package com.example.demo.api.dto.usuario;

import com.example.demo.domain.enums.Roles;
import com.example.demo.domain.model.Filme;
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
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Long idUsuario;
    private String nome;
    private String email;
    private String usuario;
    private Date dataRegistro = new Date();
    private Date dataNascimento;
    private Roles role = Roles.ROLE_USER;
    private List<Filme> filmes;

}
