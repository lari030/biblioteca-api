package com.larissa.meuprojeto.exceptions.general;

public class LivroJaEmprestadoExcecao extends RuntimeException {
    public LivroJaEmprestadoExcecao() {
        super("Este livro já está emprestado e ainda não foi devolvido.");
    }
}

