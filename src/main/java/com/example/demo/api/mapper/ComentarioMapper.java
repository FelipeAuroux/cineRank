package com.example.demo.api.mapper;

import com.example.demo.api.dto.comentario.ComentarioRequestDTO;
import com.example.demo.api.dto.comentario.ComentarioResponseDTO;
import com.example.demo.domain.model.Comentario;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ComentarioMapper {

    public static Comentario converterComentarioRequestDTOEmComentarioEntidade(ComentarioRequestDTO comentarioRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(comentarioRequestDTO, Comentario.class);
    }

    public static ComentarioResponseDTO converterComentarioEntidadeEmComentarioResponseDTO(Comentario comentario){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(comentario, ComentarioResponseDTO.class);
    }
    public static List<ComentarioResponseDTO> converterListaDeComentarioEntidadeParaListaDeComentarioResponseDTO(List<Comentario> listaComentario) {
        ModelMapper modelMapper = new ModelMapper();
        return listaComentario.stream()
                .map(comentario -> modelMapper.map(comentario, ComentarioResponseDTO.class))
                .collect(Collectors.toList());
    }
}
