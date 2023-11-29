package com.example.demo.api.mapper;

import com.example.demo.api.dto.UsuarioRequestDTO;
import com.example.demo.api.dto.UsuarioResponseDTO;
import com.example.demo.domain.model.Usuario;
import org.modelmapper.ModelMapper;

public class UsuarioMapper {

    public static Usuario converterUsuarioRequestDTOEmUsuarioEntidade(UsuarioRequestDTO usuarioRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuarioRequestDTO, Usuario.class);
    }

    public static UsuarioResponseDTO converterUsuarioEntidadeParaUsuarioResponseDTO(Usuario usuario){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }
}
