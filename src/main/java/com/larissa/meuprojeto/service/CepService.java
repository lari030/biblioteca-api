package com.larissa.meuprojeto.service;


import com.larissa.meuprojeto.data.dto.response.CepResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

    //Consulta o serviço ViaCEP para obter os dados de endereço a partir do CEP informado
    public CepResponse consultarCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CepResponse> cepResponse = restTemplate.getForEntity(url, CepResponse.class);

        return cepResponse.getBody();
        

       
}
}