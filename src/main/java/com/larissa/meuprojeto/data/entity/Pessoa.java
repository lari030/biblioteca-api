package com.larissa.meuprojeto.data.entity;

import com.larissa.meuprojeto.data.dto.request.PessoaRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idPessoa;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    @Column(nullable = false, length = 9)
    private String cep;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(length = 100)
    private String logradouro;

    @Column(length = 100)
    private String bairro;

    @Column(length = 100)
    private String cidade;

    @Column(length = 2, name = "estado")
    private String uf;  

  
      public Pessoa(PessoaRequest pessoaRequest) {
        this.nome = pessoaRequest.nome();
        this.cpf = pessoaRequest.cpf();
        this.cep = pessoaRequest.cep();
        this.email = pessoaRequest.email();
        this.senha = pessoaRequest.senha();
    }
   
}
