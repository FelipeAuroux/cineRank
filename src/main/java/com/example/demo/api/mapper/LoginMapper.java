package com.example.demo.api.mapper;

import com.example.demo.api.dto.login.LoginRequestDTO;
import com.example.demo.api.dto.login.LoginResponseDTO;
import com.example.demo.domain.model.Usuario;
import org.modelmapper.ModelMapper;

public class LoginMapper
{

    public static Usuario mapperloginRequestDTOToUsuario(LoginRequestDTO loginRequestDTO)
    {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(loginRequestDTO, Usuario.class);
    }

    public static LoginResponseDTO mapperUsuarioToLoginResponseDTO(Usuario Usuario){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(Usuario,LoginResponseDTO.class);
    }
}
