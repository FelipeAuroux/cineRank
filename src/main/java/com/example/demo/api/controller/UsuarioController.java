package com.example.demo.api.controller;

import com.example.demo.api.dto.usuario.UsuarioComIDRequestDTO;
import com.example.demo.api.dto.usuario.UsuarioRequestDTO;
import com.example.demo.api.dto.usuario.UsuarioResponseDTO;
import com.example.demo.api.mapper.UsuarioMapper;
import com.example.demo.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuarioResponseDTO = UsuarioMapper.converterUsuarioEntidadeParaUsuarioResponseDTO(service.criarNovoUsuario(UsuarioMapper.converterUsuarioRequestDTOEmUsuarioEntidade(usuarioRequestDTO)));
        return new ResponseEntity<UsuarioResponseDTO>(usuarioResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarUsuario(@RequestBody @Valid UsuarioComIDRequestDTO usuarioComIDRequestDTO) {
        return ResponseEntity.ok(UsuarioMapper.converterUsuarioEntidadeParaUsuarioResponseDTO(service.atualizarUsuarioPorId(UsuarioMapper.converterUsuarioComIDRequestDTOEmUsuarioEntidade(usuarioComIDRequestDTO))));
    }

    @DeleteMapping("/deletar/{idUsuario}")
    public ResponseEntity<?> deletarUsuarioPorId(@PathVariable("idUsuario") @Valid Long idUsuario){
        service.deletarUsuarioPorId(idUsuario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/{idUsuario}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable("idUsuario") @Valid Long idUsuario) {
        return ResponseEntity.ok(UsuarioMapper.converterUsuarioEntidadeParaUsuarioResponseDTO(service.buscarUsuarioPorId(idUsuario)));
    }

    @GetMapping("listar-todos")
    public ResponseEntity<?> listarTodosUsuarios() {
        return ResponseEntity.ok(UsuarioMapper.converterListaDeUsuariosEntidadeParaListaDeUsuarioResponseDTO(service.listarTodosUsuarios()));
    }

}
