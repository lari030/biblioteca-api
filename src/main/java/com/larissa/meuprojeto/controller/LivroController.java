package com.larissa.meuprojeto.controller;

import com.larissa.meuprojeto.data.dto.request.LivroRequest;
import com.larissa.meuprojeto.data.dto.response.LivroResponse;
import com.larissa.meuprojeto.service.LivroService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;



import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@Tag(name = "Livro", description = "Endpoints relacionados à área de livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Operation(
        summary = "Lista todos os livros",
        description = "Retorna todos os livros cadastrados.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = LivroResponse.class)))),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @GetMapping("/livros")
    public ResponseEntity<List<LivroResponse>> listarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.listarTodos());
    }

    @Operation(
        summary = "Busca um livro por ID",
        description = "Retorna um livro específico com base no ID fornecido.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                content = @Content(schema = @Schema(implementation = LivroResponse.class))),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @GetMapping("/{idLivro}")
    public ResponseEntity<LivroResponse> buscarPorId(@PathVariable Long idLivro) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.buscarPorId(idLivro));
    }

    @Operation(
        summary = "Cria um novo livro",
        description = "Adiciona um novo livro com os dados fornecidos.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso",
                content = @Content(schema = @Schema(implementation = LivroResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @PostMapping("/criar")
    public ResponseEntity<LivroResponse> criarLivro(@Valid @RequestBody LivroRequest livroRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.criarLivro(livroRequest));
    }

    @Operation(
        summary = "Atualiza um livro",
        description = "Atualiza os dados de um livro existente com base no ID fornecido.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso",
                content = @Content(schema = @Schema(implementation = LivroResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @PutMapping("/atualizar/{idLivro}")
    public ResponseEntity<LivroResponse> atualizarLivro(@PathVariable Long idLivro, @RequestBody LivroRequest livroRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.atualizarLivro(idLivro, livroRequest));
    }

    @Operation(
        summary = "Deleta um livro",
        description = "Remove um livro do sistema com base no ID fornecido.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Livro deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @DeleteMapping("/delete/{idLivro}")
    public ResponseEntity<String> deletarLivro(@PathVariable Long idLivro) {
        livroService.deletarLivro(idLivro);
        return ResponseEntity.ok("Livro deletado com sucesso.");
    }
}
