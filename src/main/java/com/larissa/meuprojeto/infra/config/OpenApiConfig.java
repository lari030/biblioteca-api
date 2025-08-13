package com.larissa.meuprojeto.infra.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
     @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Documentação da API Biblioteca") //titulo da documentacao
                        .version("v1") //versao da documentacao
                        .description("API processo trainee") //descricao da documentacao
                )
                .servers(Arrays.asList(
                        new Server().url("http://localhost:8080").description("Servidor local")
                ));
    }
}