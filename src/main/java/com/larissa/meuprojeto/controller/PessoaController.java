package com.larissa.meuprojeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.larissa.meuprojeto.data.dto.request.PessoaRequest;
import com.larissa.meuprojeto.data.dto.response.PessoaResponse;
import com.larissa.meuprojeto.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/pessoas")
@Tag(name = "Pessoa", description = "Endpoints relacionados à área de pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Operation(
        summary = "Lista todas as pessoas",
        description = "Retorna todas as pessoas cadastradas.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = PessoaResponse.class)))),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @GetMapping("/all")
    public ResponseEntity<List<PessoaResponse>> listarTodas() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarTodas());
    }

    @Operation(
        summary = "Busca uma pessoa por ID",
        description = "Retorna uma pessoa específica com base no ID fornecido.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                content = @Content(schema = @Schema(implementation = PessoaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @GetMapping("/{idPessoa}")
    public ResponseEntity<PessoaResponse> buscarPorId(@PathVariable Long idPessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscarPorId(idPessoa));
    }

    @Operation(
        summary = "Cria uma nova pessoa",
        description = "Adiciona uma nova pessoa com os dados fornecidos.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso",
                content = @Content(schema = @Schema(implementation = PessoaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @PostMapping("/criar")
    public ResponseEntity<PessoaResponse> criarPessoa(@Valid @RequestBody PessoaRequest pessoaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.criarPessoa(pessoaRequest));
    }

    @Operation(
        summary = "Atualiza uma pessoa",
        description = "Atualiza os dados de uma pessoa existente com base no ID fornecido.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso",
                content = @Content(schema = @Schema(implementation = PessoaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @PutMapping("/{idPessoa}")
    public ResponseEntity<PessoaResponse> atualizarPessoa(@PathVariable Long idPessoa, @RequestBody PessoaRequest pessoaRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.atualizarPessoa(idPessoa, pessoaRequest));
    }

    @Operation(
        summary = "Deleta uma pessoa",
        description = "Remove uma pessoa do sistema com base no ID fornecido.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Pessoa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @DeleteMapping("/{idPessoa}")
    public ResponseEntity<String> deletarPessoa(@PathVariable Long idPessoa) {
        pessoaService.deletarPessoa(idPessoa);
        return ResponseEntity.ok("Pessoa deletada com sucesso.");
    }
}
