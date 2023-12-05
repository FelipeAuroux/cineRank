package com.example.demo.domain.model;

import com.example.demo.domain.enums.Roles;
import com.example.demo.utils.RespostaDeAtributoPersonalizada;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements UserDetails {

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
//    @Column(columnDefinition = "text")
//    private String token;

    // Relacionamentos

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToOne(mappedBy = "usuario")
    private Lista lista;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Filme> filmes;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Avaliacao> avaliacoes;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @JsonIgnore
    @ManyToMany(mappedBy = "usuarios")
    private List<Sessao> sessoes;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario") // usuário admin cadastra
    private List<Cinema> cinemas;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Roles.ROLE_ADMIN) {
            // se esse usuário tiver uma role de admin, retornamos os tipos de acesso que ele pode ter no sistema, nesse caso admin e user
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }

        if (this.role == Roles.ROLE_USER) {
            // se esse usuário tiver uma role de user, retornamos o tipo de acesso que ele pode ter no sistema, nesse caso apenas user
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return null;
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
