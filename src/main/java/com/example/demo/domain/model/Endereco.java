package com.example.demo.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Endereco {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;
    @NotBlank
    private String rua;
    private String numero;
    @NotBlank
    private String bairro;
    @NotNull @Size(min = 8, max = 8)
    private int cep;
    @NotBlank
    private String cidade;
    @NotBlank @Size(min = 2, max = 2)
    private String uf;
}
