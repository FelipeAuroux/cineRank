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
public class Lista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLista;
    @NotNull
    private Date adicao;

    // Relacionamentos

    @OneToMany(mappedBy = "lista")
    private List<Filme> filmes;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
