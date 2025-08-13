package com.larissa.meuprojeto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.media.Content;
import com.larissa.meuprojeto.data.dto.response.CepResponse;
import com.larissa.meuprojeto.service.CepService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cep")
@Tag(name = "CEP", description = "Endpoints relacionados à consulta de CEP")
public class CepController {

    private CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @Operation(
        summary = "Consulta endereço por CEP",
        description = "Retorna os dados de endereço correspondentes ao CEP fornecido.",
        responses = {
            @ApiResponse(responseCode = "200", description = "CEP encontrado",
                content = @Content(schema = @Schema(implementation = CepResponse.class))),
            @ApiResponse(responseCode = "404", description = "CEP não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
        }
    )
    @GetMapping("/{cep}")
    public ResponseEntity<CepResponse> consultarCep(@PathVariable String cep) {
        CepResponse endereco = cepService.consultarCep(cep);
        if (endereco == null || endereco.getLogradouro() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(endereco);
    }
}
