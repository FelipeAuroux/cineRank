package com.example.demo.api.controller;

import com.example.demo.api.dto.UsuarioRequestDTO;
import com.example.demo.api.dto.UsuarioResponseDTO;
import com.example.demo.api.mapper.UsuarioMapper;
import com.example.demo.domain.model.Usuario;
import com.example.demo.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Operation(description = "Endpoint de cadastrar novo usuário", method = "POST")
    @PostMapping("/novo")
    public ResponseEntity<?> novo(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = service.salvar(UsuarioMapper.converterUsuarioRequestDTOEmUsuarioEntidade(usuarioRequestDTO));
        UsuarioResponseDTO usuarioResponseDTO = UsuarioMapper.converterUsuarioEntidadeParaUsuarioResponseDTO(usuario);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

}
