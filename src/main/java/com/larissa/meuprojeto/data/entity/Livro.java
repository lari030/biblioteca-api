package com.larissa.meuprojeto.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import com.larissa.meuprojeto.data.dto.request.LivroRequest;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idLivro;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String autor;

    @Column(nullable = false)
    private LocalDate dataLancamento;

    public Livro(LivroRequest livroRequest) {
        this.nome = livroRequest.nome();
        this.autor = livroRequest.autor();
        this.dataLancamento = livroRequest.dataLancamento();
    }

}
