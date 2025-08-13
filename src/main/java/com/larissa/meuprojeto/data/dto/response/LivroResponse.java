package com.larissa.meuprojeto.data.dto.response;

import java.time.LocalDate;
import com.larissa.meuprojeto.data.entity.Livro;

public record LivroResponse(
    Long idLivro,
    String nome,
    String autor,
    LocalDate dataLancamento
) {
    public LivroResponse(Livro livro) {
        this(
            livro.getIdLivro(),
            livro.getNome(),
            livro.getAutor(),
            livro.getDataLancamento()
        );
    }
}
