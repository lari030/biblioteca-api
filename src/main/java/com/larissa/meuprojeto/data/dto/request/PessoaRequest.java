package com.larissa.meuprojeto.data.dto.request;

import jakarta.validation.constraints.*;



public record PessoaRequest(
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100)
    String nome,

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos numéricos")
    String cpf,

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP deve ter formato válido")
    String cep,

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Size(max = 100)
    String email,

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 100)
    String senha
) {}