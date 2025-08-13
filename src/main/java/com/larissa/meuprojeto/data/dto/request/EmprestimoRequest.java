package com.larissa.meuprojeto.data.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record EmprestimoRequest(

    @NotNull(message = "ID da pessoa é obrigatório")
    Long pessoaId,
    
    @NotNull(message = "ID do livro é obrigatório")
    Long livroId,
    
    @NotNull(message = "Data de empréstimo é obrigatória")
    LocalDate dataEmprestimo,

    LocalDate dataDevolucao
) {
}
