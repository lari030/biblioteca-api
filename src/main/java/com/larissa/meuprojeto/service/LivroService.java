package com.larissa.meuprojeto.service;

import com.larissa.meuprojeto.data.dto.request.LivroRequest;
import com.larissa.meuprojeto.data.dto.response.LivroResponse;
import com.larissa.meuprojeto.data.entity.Livro;
import com.larissa.meuprojeto.exceptions.general.EntidadeNaoEncontradaExcecao;
import com.larissa.meuprojeto.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;
    
    //listar todos os livros existentes
    public List<LivroResponse> listarTodos() {
        List<Livro> livros = livroRepository.findAll();
        return livros.stream().map(LivroResponse::new).collect(Collectors.toList());
    }
    //buscar livro por id
    public LivroResponse buscarPorId(Long idLivro) {
        Livro livro = buscarLivroPorId(idLivro);
        return new LivroResponse(livro);
    }
    //cadastrar um novo livro
    public LivroResponse criarLivro(LivroRequest livroRequest) {
        Livro livro = new Livro();
        livro.setNome(livroRequest.nome());
        livro.setAutor(livroRequest.autor());
        livro.setDataLancamento(livroRequest.dataLancamento());

        livroRepository.save(livro);
        return new LivroResponse(livro);
    }
    //atualizar os dados de um livro 
    public LivroResponse atualizarLivro(Long id, LivroRequest livroRequest) {
        Livro livro = buscarLivroPorId(id);

        livro.setNome(livroRequest.nome());
        livro.setAutor(livroRequest.autor());
        livro.setDataLancamento(livroRequest.dataLancamento());

        livroRepository.save(livro);
        return new LivroResponse(livro);
    }
    //deletar um livro
    public void deletarLivro(Long id) {
        Livro livro = buscarLivroPorId(id);
        livroRepository.delete(livro);
    }

    private Livro buscarLivroPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaExcecao(id));
    }
}
