package com.larissa.meuprojeto.service;

import com.larissa.meuprojeto.data.dto.request.PessoaRequest;
import com.larissa.meuprojeto.data.dto.response.CepResponse;
import com.larissa.meuprojeto.data.dto.response.PessoaResponse;
import com.larissa.meuprojeto.data.entity.Pessoa;
import com.larissa.meuprojeto.repository.PessoaRepository;
import com.larissa.meuprojeto.exceptions.general.EntidadeNaoEncontradaExcecao;
import com.larissa.meuprojeto.exceptions.general.RecursoDuplicadoExcecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private CepService viaCepService;

    //listar todas as pessoas
   public List<PessoaResponse> listarTodas() {
    List<Pessoa> pessoas = pessoaRepository.findAll();
    return pessoas.stream().map(PessoaResponse::new).collect(Collectors.toList());
    }
    //buscar pessoa por id
    public PessoaResponse buscarPorId(Long idPessoa) {
        Pessoa pessoa = buscarPessoaPorId(idPessoa);
        return new PessoaResponse(pessoa);
    }

    //cadastrar uma nova pessoa
    public PessoaResponse criarPessoa(PessoaRequest pessoaRequest) {
    if (pessoaRepository.existsByCpf(pessoaRequest.cpf())) {
    throw new RecursoDuplicadoExcecao("CPF já cadastrado.");
    }

    if (pessoaRepository.existsByEmail(pessoaRequest.email())) {
    throw new RecursoDuplicadoExcecao("Email já cadastrado.");
    }


    Pessoa pessoa = new Pessoa();
    pessoa.setNome(pessoaRequest.nome());
    pessoa.setCpf(pessoaRequest.cpf());
    pessoa.setCep(pessoaRequest.cep());
    pessoa.setEmail(pessoaRequest.email());
    pessoa.setSenha(pessoaRequest.senha());

    CepResponse endereco = viaCepService.consultarCep(pessoaRequest.cep());
    if (endereco == null || endereco.getLogradouro() == null) {
        throw new IllegalArgumentException("CEP inválido ou não encontrado.");
    }

    pessoa.setLogradouro(endereco.getLogradouro());
    pessoa.setBairro(endereco.getBairro());
    pessoa.setCidade(endereco.getLocalidade());  
    pessoa.setUf(endereco.getUf());

    pessoaRepository.save(pessoa);
    return new PessoaResponse(pessoa);
    }
    //atualizar os dados de uma pessoa
    public PessoaResponse atualizarPessoa(Long id, PessoaRequest pessoaRequest) {
    Pessoa pessoa = buscarPessoaPorId(id);

    pessoa.setNome(pessoaRequest.nome());
    pessoa.setCpf(pessoaRequest.cpf());
    pessoa.setCep(pessoaRequest.cep());
    pessoa.setEmail(pessoaRequest.email());
    pessoa.setSenha(pessoaRequest.senha());

    CepResponse endereco = viaCepService.consultarCep(pessoaRequest.cep());
    if (endereco == null || endereco.getLogradouro() == null) {
        throw new IllegalArgumentException("CEP inválido ou não encontrado.");
    }

    pessoa.setLogradouro(endereco.getLogradouro());
    pessoa.setBairro(endereco.getBairro());
    pessoa.setCidade(endereco.getLocalidade());
    pessoa.setUf(endereco.getUf());

    pessoaRepository.save(pessoa);
    return new PessoaResponse(pessoa);
    }
    //deletar um livro
    public void deletarPessoa(Long id) {
        Pessoa pessoa = buscarPessoaPorId(id);
        pessoaRepository.delete(pessoa);
    }

    private Pessoa buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaExcecao(id));
    }
}
