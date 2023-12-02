package com.example.demo.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cinema {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCinema;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotBlank
    private String funcionamento;
    @NotNull
    private int salas;
    @CNPJ
    private int cnpj;

    // Relacionamentos

    @ManyToMany(mappedBy = "cinemas")
    private List<Sessao> sessoes;

}
