package com.example.demo.api.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequestDTO {

    @NotBlank
    private String rua;
    private String numero;
    @NotBlank
    private String bairro;
    @NotNull
    @Size(min = 9, max = 9)
    private String cep;
    @NotBlank
    private String cidade;
    @NotBlank
    @Size(min = 2, max = 2)
    private String uf;

}
