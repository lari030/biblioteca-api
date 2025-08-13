package com.larissa.meuprojeto.controller;

import com.larissa.meuprojeto.data.dto.request.EmprestimoRequest;
import com.larissa.meuprojeto.data.dto.response.EmprestimoResponse;
import com.larissa.meuprojeto.service.EmprestimoService;

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
@RequestMapping("/emprestimos")
@Tag(name = "Emprestimo", description = "Endpoints relacionados à área de empréstimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Operation(
        summary = "Lista todos os empréstimos",
        description = "Retorna uma lista de todos os empréstimos cadastrados no sistema.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                         content = @Content (array = @ArraySchema(schema = @Schema(implementation = EmprestimoResponse.class)))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
        }
    )
    @GetMapping("/all")
    public ResponseEntity<List<EmprestimoResponse>> listarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.listarTodos());
    }

    @Operation(
        summary = "Busca um empréstimo por ID",
        description = "Retorna os detalhes de um empréstimo com base no ID fornecido.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Empréstimo encontrado",
                         content = @Content(schema = @Schema(implementation = EmprestimoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
        }
    )
    @GetMapping("/{idEmprestimo}")
    public ResponseEntity<EmprestimoResponse> buscarPorId(@PathVariable Long idEmprestimo) {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.buscarPorId(idEmprestimo));
    }

    @Operation(
        summary = "Cria um novo empréstimo",
        description = "Registra um novo empréstimo de livro para uma pessoa.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Empréstimo criado com sucesso",
                         content = @Content(schema = @Schema(implementation = EmprestimoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
        }
    )
    @PostMapping("/criar")
    public ResponseEntity<EmprestimoResponse> criarEmprestimo(@Valid @RequestBody EmprestimoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoService.criarEmprestimo(request));
    }

    @Operation(
        summary = "Atualiza um empréstimo existente",
        description = "Modifica os dados de um empréstimo com base no ID fornecido.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Empréstimo atualizado com sucesso",
                         content = @Content(schema = @Schema(implementation = EmprestimoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @PutMapping("/atualizar/{idEmprestimo}")
    public ResponseEntity<EmprestimoResponse> atualizarEmprestimo(
            @PathVariable Long idEmprestimo,
            @RequestBody EmprestimoRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.atualizarEmprestimo(idEmprestimo, request));
    }

    @Operation(
        summary = "Deleta um empréstimo",
        description = "Remove um empréstimo com base no ID fornecido.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Empréstimo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @DeleteMapping("/delete/{idEmprestimo}")
    public ResponseEntity<String> deletarEmprestimo(@PathVariable Long idEmprestimo) {
        emprestimoService.deletarEmprestimo(idEmprestimo);
        return ResponseEntity.ok("Empréstimo deletado com sucesso.");
    }

    @Operation(
        summary = "Devolve um livro",
        description = "Registra a devolução do livro emprestado com base no ID do empréstimo.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Livro devolvido com sucesso",
                         content = @Content(schema = @Schema(implementation = EmprestimoResponse.class))),
            @ApiResponse(responseCode = "400", description = "O livro já foi devolvido"),
            @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @PutMapping("/devolver/{idEmprestimo}")
    public ResponseEntity<EmprestimoResponse> devolverLivro(@PathVariable Long idEmprestimo) {
        return ResponseEntity.ok(emprestimoService.devolverLivro(idEmprestimo));
    }

}
