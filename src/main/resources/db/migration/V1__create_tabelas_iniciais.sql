CREATE TABLE IF NOT EXISTS pessoa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    cpf VARCHAR(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS livro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    data_lancamento DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS emprestimo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pessoa_id BIGINT NOT NULL,
    livro_id BIGINT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE,
    CONSTRAINT fk_emprestimo_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa(id),
    CONSTRAINT fk_emprestimo_livro FOREIGN KEY (livro_id) REFERENCES livro(id)
);
