package com.larissa.meuprojeto.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.larissa.meuprojeto.data.dto.response.CepResponse;
import com.larissa.meuprojeto.service.CepService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/cep")
public class CepController {
    private CepService cepService;

 
    public CepController (CepService cepService)
    {
        this.cepService = cepService; 
    }

    @GetMapping("/{cep}")
    public ResponseEntity<CepResponse> consultarCep(@PathVariable String cep){
        return ResponseEntity.ok(cepService.consultarCep(cep));
    }
    
    
}
