package com.larissa.meuprojeto.exceptions.general;

public class EntidadeNaoEncontradaExcecao extends RuntimeException {
    
    public EntidadeNaoEncontradaExcecao (Long id){
        super("Entidade com o id: " + id +" não encontrada");
    }
}
