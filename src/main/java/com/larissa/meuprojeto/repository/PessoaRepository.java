
package com.larissa.meuprojeto.repository;

import com.larissa.meuprojeto.data.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
