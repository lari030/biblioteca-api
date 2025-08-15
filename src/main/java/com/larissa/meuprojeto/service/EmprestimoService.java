package com.larissa.meuprojeto.service;

import com.larissa.meuprojeto.data.dto.request.EmprestimoRequest;
import com.larissa.meuprojeto.data.dto.response.EmprestimoResponse;
import com.larissa.meuprojeto.data.entity.Emprestimo;
import com.larissa.meuprojeto.data.entity.Livro;
import com.larissa.meuprojeto.data.entity.Pessoa;
import com.larissa.meuprojeto.exceptions.general.LivroJaEmprestadoExcecao;
import com.larissa.meuprojeto.exceptions.general.EntidadeNaoEncontradaExcecao;
import com.larissa.meuprojeto.repository.EmprestimoRepository;
import com.larissa.meuprojeto.repository.LivroRepository;
import com.larissa.meuprojeto.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private LivroRepository livroRepository;

    //listar todos os emprestimos existentes
    public List<EmprestimoResponse> listarTodos() {
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        return emprestimos.stream().map(EmprestimoResponse::new).collect(Collectors.toList());
    }
    //buscar emprestimo por id
    public EmprestimoResponse buscarPorId(Long id) {
        Emprestimo emprestimo = buscarEmprestimoPorId(id);
        return new EmprestimoResponse(emprestimo);
    }
    //criar um emprestimo
   public EmprestimoResponse criarEmprestimo(EmprestimoRequest emprestimoRequest) {
         Pessoa pessoa = pessoaRepository.findById(emprestimoRequest.pessoaId())
            .orElseThrow(() -> new EntidadeNaoEncontradaExcecao(emprestimoRequest.pessoaId()));

         Livro livro = livroRepository.findById(emprestimoRequest.livroId())
            .orElseThrow(() -> new EntidadeNaoEncontradaExcecao(emprestimoRequest.livroId()));

         boolean livroJaEmprestado = emprestimoRepository
            .existsByLivroAndDataDevolucaoIsNull(livro);

        if (livroJaEmprestado) {
        throw new LivroJaEmprestadoExcecao();
    }
        Emprestimo emprestimo = new Emprestimo(emprestimoRequest, pessoa, livro);
        emprestimoRepository.save(emprestimo);
         return new EmprestimoResponse(emprestimo);
    }
    //atualizar um emprestimo existente
    public EmprestimoResponse atualizarEmprestimo(Long id, EmprestimoRequest request) {
        Emprestimo emprestimo = buscarEmprestimoPorId(id);

        Pessoa pessoa = pessoaRepository.findById(request.pessoaId())
                .orElseThrow(() -> new EntidadeNaoEncontradaExcecao(id));

        Livro livro = livroRepository.findById(request.livroId())
                .orElseThrow(() -> new EntidadeNaoEncontradaExcecao(id));

        emprestimo.setPessoa(pessoa);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(request.dataEmprestimo());
        emprestimo.setDataDevolucao(request.dataDevolucao());

        emprestimoRepository.save(emprestimo);
        return new EmprestimoResponse(emprestimo);
    }
    //deletar um emprestimo
    public void deletarEmprestimo(Long id) {
        Emprestimo emprestimo = buscarEmprestimoPorId(id);
        emprestimoRepository.delete(emprestimo);
    }
    //realizar uma devolução
    public EmprestimoResponse devolverLivro(Long idEmprestimo) {
    Emprestimo emprestimo = buscarEmprestimoPorId(idEmprestimo);

    if (emprestimo.getDataDevolucao() != null) {
        throw new EntidadeNaoEncontradaExcecao(idEmprestimo);
    }
    emprestimo.setDataDevolucao(LocalDate.now());

    emprestimoRepository.save(emprestimo);

    return new EmprestimoResponse(emprestimo);
    }
    
    private Emprestimo buscarEmprestimoPorId(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaExcecao(id));
}
}