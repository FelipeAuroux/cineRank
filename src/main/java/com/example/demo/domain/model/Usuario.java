package com.example.demo.domain.model;

import com.example.demo.domain.enums.Roles;
import com.example.demo.utils.RespostaDeAtributoPersonalizada;
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
    @NotBlank(message = RespostaDeAtributoPersonalizada.USUARIO_ATR_NOME_VAZIO)
    private String nome;
    @Email
    @NotBlank(message = RespostaDeAtributoPersonalizada.USUARIO_ATR_EMAIL_VAZIO)
    private String email;
    @NotBlank(message = RespostaDeAtributoPersonalizada.USUARIO_ATR_USUARIO_VAZIO)
    @Size(min = 6, max = 20)
    private String usuario;
    @NotBlank(message = RespostaDeAtributoPersonalizada.USUARIO_ATR_SENHA_VAZIO)
    @Size(min = 8)
    private String senha;
    @NotNull
    private Date dataRegistro = new Date();
    @NotNull(message = RespostaDeAtributoPersonalizada.USUARIO_ATR_DATANASCIMENTO_VAZIO)
    private Date dataNascimento;
    @CPF
    @Column(unique = true)
    private String cpf;
    @Enumerated
    @NotNull
    private Roles role;

    // Relacionamentos

    @OneToOne(mappedBy = "usuario")
    private Endereco endereco;

    @OneToMany(mappedBy = "usuario")
    private List<Filme> filmes;

    @OneToMany(mappedBy = "usuario")
    private List<Avaliacao> avaliacoes;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @ManyToMany(mappedBy = "usuarios")
    private List<Sessao> sessoes;

    @OneToOne(mappedBy = "usuario")
    private Lista lista;

}
