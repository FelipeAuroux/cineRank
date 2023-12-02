package com.example.demo.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSessao;
    @NotNull
    private Date horario;
    @NotNull
    private int ingressos;
    @NotNull
    private int sala;
    @NotNull
    private int codSessao;

    // Relacionamentos

    @ManyToMany
    @JoinTable(
            name = "sessao_usuario",
            joinColumns = @JoinColumn(name = "sessao_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios;

    @ManyToMany
    @JoinTable(
            name = "sessao_filme",
            joinColumns = @JoinColumn(name = "sessao_id"),
            inverseJoinColumns = @JoinColumn(name = "filme_id")
    )
    private List<Filme> filmes;

    @ManyToMany
    @JoinTable(
            name = "sessao_cinema",
            joinColumns = @JoinColumn(name = "sessao_id"),
            inverseJoinColumns = @JoinColumn(name = "cinema_id")
    )
    private List<Cinema> cinemas;


}
