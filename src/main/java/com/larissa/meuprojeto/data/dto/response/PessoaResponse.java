package com.larissa.meuprojeto.data.dto.response;

import com.larissa.meuprojeto.data.entity.Pessoa;

public record PessoaResponse(
    Long id,
    String nome,
    String cpf,
    String cep,
    String email,
    String logradouro,
    String bairro,
    String cidade,
    String uf
) {
    public PessoaResponse(Pessoa pessoa) {
        this(
            pessoa.getIdPessoa(),
            pessoa.getNome(),
            pessoa.getCpf(),
            pessoa.getCep(),
            pessoa.getEmail(),
            pessoa.getLogradouro(),
            pessoa.getBairro(),
            pessoa.getCidade(),
            pessoa.getUf()
        );
    }
}


