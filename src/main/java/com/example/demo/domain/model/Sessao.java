package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.transaction.support.ResourceHolderSupport;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sessao extends ResourceHolderSupport {

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
            name = "sessao_filme",
            joinColumns = @JoinColumn(name = "sessao_id"),
            inverseJoinColumns = @JoinColumn(name = "filme_id")
    )
    private List<Filme> filmes;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "sessao_cinema",
            joinColumns = @JoinColumn(name = "sessao_id"),
            inverseJoinColumns = @JoinColumn(name = "cinema_id")
    )
    private List<Cinema> cinemas;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "sessao_usuario",
            joinColumns = @JoinColumn(name = "sessao_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios;

}
