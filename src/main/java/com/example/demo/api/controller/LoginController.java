package com.example.demo.api.controller;


import com.example.demo.api.dto.LoginInputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/login")
@Tag(name = "Login")
public class LoginController {

    @Operation(description = "Endpoint de login", method = "POST")
    @PostMapping("/logar")
    public ResponseEntity<?> login(@RequestBody @Valid LoginInputDTO loginInputDTO) {
        try {
            System.out.println("Login = " + loginInputDTO.getUsuario() + " - " + loginInputDTO.getSenha());
        } catch (ValidationException validationException) {
            System.out.println(validationException.getMessage());
        }
        return ResponseEntity.ok("Funcionando");
    }
}
