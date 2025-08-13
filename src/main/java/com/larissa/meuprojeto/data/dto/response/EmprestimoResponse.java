package com.larissa.meuprojeto.data.dto.response;

import java.time.LocalDate;
import com.larissa.meuprojeto.data.entity.Emprestimo;

public record EmprestimoResponse(
    Long idEmprestimo,
    Long pessoaId,
    Long livroId,
    LocalDate dataEmprestimo,
    LocalDate dataDevolucao
) {
    public EmprestimoResponse(Emprestimo emprestimo) {
        this(
            emprestimo.getIdEmprestimo(),
            emprestimo.getPessoa().getIdPessoa(),
            emprestimo.getLivro().getIdLivro(),
            emprestimo.getDataEmprestimo(),
            emprestimo.getDataDevolucao()
        );
    }
}
