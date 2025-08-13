package com.larissa.meuprojeto.repository;

import com.larissa.meuprojeto.data.entity.Emprestimo;
import com.larissa.meuprojeto.data.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
boolean existsByLivroAndDataDevolucaoIsNull(Livro livro);

}