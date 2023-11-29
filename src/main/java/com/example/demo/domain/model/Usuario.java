package com.example.demo.domain.model;

import com.example.demo.domain.enums.Roles;
import jakarta.persistence.*;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    @NotBlank
    private String nome;
    @Email @NotBlank
    private String email;
    @NotBlank @Size(min = 6, max = 20)
    private String usuario;
    @NotBlank @Size(min = 8)
    private String senha;
    @NotNull
    private Date dataRegistro;
    @NotNull
    private Date dataNascimento;
    @CPF @Column(unique = true)
    private String cpf;
    @Enumerated @NotNull
    private Roles role;

    @OneToMany(mappedBy = "usuario")
    private List<Filme> filmes;

}
