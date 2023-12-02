package com.example.demo.api.mapper;

import com.example.demo.api.dto.filme.FilmeRequestDTO;
import com.example.demo.api.dto.filme.FilmeResponseDTO;
import com.example.demo.domain.model.Filme;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class FilmeMapper {

    public static Filme converterFilmeRequestDTOParaFilmeEntidade(FilmeRequestDTO filmeRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(filmeRequestDTO, Filme.class);
    }

    public static FilmeResponseDTO converterFilmeEntidadeParaFilmeResponseDTO(Filme filme){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(filme, FilmeResponseDTO.class);
    }

    public static List<FilmeResponseDTO> converterListaDeFilmeEntidadeParaListaDeFilmeResponseDTO(List<Filme> listaFilme) {
        ModelMapper modelMapper = new ModelMapper();
        return listaFilme.stream()
                .map(filme -> modelMapper.map(filme, FilmeResponseDTO.class))
                .collect(Collectors.toList());
    }


}
