package com.larissa.meuprojeto.data.dto.response;

import java.time.LocalDate;
import com.larissa.meuprojeto.data.entity.Emprestimo;


public record EmprestimoResponse(
        Long idEmprestimo,
        Long idPessoa,
        String nomePessoa,
        Long idLivro,
        String nomeLivro,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {
    public EmprestimoResponse(Emprestimo emprestimo) {
        this(
            emprestimo.getIdEmprestimo(),
            emprestimo.getPessoa().getIdPessoa(),
            emprestimo.getPessoa().getNome(),
            emprestimo.getLivro().getIdLivro(),
            emprestimo.getLivro().getNome(),
            emprestimo.getDataEmprestimo(),
            emprestimo.getDataDevolucao()
        );
    }
}
