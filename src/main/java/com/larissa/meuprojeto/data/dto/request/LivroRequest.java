package com.larissa.meuprojeto.data.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.*;


public record LivroRequest(
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100)
    String nome,

    @NotBlank(message = "Autor é obrigatório")
    @Size(max = 100)
    String autor,

    @NotNull(message = "Data de lançamento é obrigatória")
    LocalDate dataLancamento
) {}
