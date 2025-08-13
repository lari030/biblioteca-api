package com.larissa.meuprojeto.exceptions;

public class LivroJaEmprestadoExcecao extends RuntimeException {
    public LivroJaEmprestadoExcecao() {
        super("Este livro já está emprestado e ainda não foi devolvido.");
    }
}
