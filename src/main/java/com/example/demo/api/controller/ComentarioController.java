package com.example.demo.api.controller;

import com.example.demo.api.dto.comentario.ComentarioRequestDTO;
import com.example.demo.api.dto.comentario.ComentarioResponseDTO;
import com.example.demo.api.mapper.ComentarioMapper;
import com.example.demo.domain.service.ComentarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/comentarios", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Comentário")
public class ComentarioController {

    @Autowired
    private ComentarioService service;
    @PostMapping("/comentar")
    public ResponseEntity<?> comentarFilme(@RequestBody @Valid ComentarioRequestDTO comentarioRequestDTO){
        return new ResponseEntity<ComentarioResponseDTO>(ComentarioMapper.converterComentarioEntidadeEmComentarioResponseDTO(service.comentarFilme(ComentarioMapper.converterComentarioRequestDTOEmComentarioEntidade(comentarioRequestDTO))), HttpStatus.CREATED);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<?> listarComentarios(){
        return ResponseEntity.ok(ComentarioMapper.converterListaDeComentarioEntidadeParaListaDeComentarioResponseDTO(service.listarTodosComentarios()));
    }


}
