package com.example.demo.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Ator {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtor;
    @NotBlank
    private String nome;
    private Date dataNascimento;

    @ManyToMany(mappedBy = "atores")
    private List<Filme> filmes;

}
