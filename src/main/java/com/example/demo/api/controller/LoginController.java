package com.example.demo.api.controller;

import com.example.demo.api.dto.login.LoginRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/login")
@Tag(name = "Login")
public class LoginController {

    @Operation(description = "Endpoint de login", method = "POST")
    @PostMapping("/logar")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {

        return ResponseEntity.ok("Funcionando");
    }
}
