package com.example.demo.api.controller;

import com.example.demo.api.dto.UsuarioRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Usuario")
public class UsuarioController {

    @Operation(description = "Endpoint de cadastrar novo usuário", method = "POST")
    @PostMapping("/novo")
    public ResponseEntity<?> novo(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {

        return ResponseEntity.ok("Funcionando");
    }

}
