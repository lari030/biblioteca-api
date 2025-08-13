package com.larissa.meuprojeto.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import com.larissa.meuprojeto.data.dto.request.EmprestimoRequest;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idEmprestimo;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @Column(nullable = false)
    private LocalDate dataEmprestimo;

    @Column(nullable = true)
    private LocalDate dataDevolucao;

     public Emprestimo(EmprestimoRequest emprestimoRequest, Pessoa pessoa, Livro livro) {
        this.pessoa = pessoa;
        this.livro = livro;
        this.dataEmprestimo = emprestimoRequest.dataEmprestimo();
        this.dataDevolucao = emprestimoRequest.dataDevolucao();
    }
   
}
