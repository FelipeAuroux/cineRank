package com.example.demo.domain.model;

import com.example.demo.domain.enums.Genero;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class Filme {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilme;
    @NotBlank
    private String titulo;
    @NotBlank
    private String diretor;
    @Enumerated @NotNull
    private Genero genero;
    @NotBlank @Size(max = 280)
    private String sinopse;
    private Date lancamento;
    @NotNull
    private int duracao;
    @NotNull
    private int classificacao;
    @NotBlank
    private String distribuidora;

    @ManyToMany
    @JoinTable(
            name = "filme_ator",
            joinColumns = @JoinColumn(name = "filme_id"),
            inverseJoinColumns = @JoinColumn(name = "ator_id")
    )
    private List<Ator> atores;

    @ManyToOne
    @JoinColumn(name = "lista_id")
    private Lista lista;

}
