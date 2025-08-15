# 📚 API Biblioteca

Sistema de gerenciamento de biblioteca desenvolvido em Spring Boot, com autenticação, controle de empréstimos e integração com API externa para busca de endereço.

## 🚀 Tecnologias Utilizadas
- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Security 
- Swagger
- MySQL
- API ViaCEP

## 📋 Funcionalidades
- CRUD de Livro
- CRUD de Pessoa
- Relação Muitos-para-Muitos entre Pessoa e Livro (entidade Empréstimo)
- Empréstimo de livros
- Devolução de livros
- Autenticação e autorização com JWT
- Integração com ViaCEP para preenchimento automático de endereço
- Tratamento de exceções

## 🔑 Autenticação
A autenticação é feita via JWT Token.  
Rotas públicas:
- `POST /auth/login`
- `POST /auth/registrar`

Rotas protegidas:
- Necessitam enviar o token no header:
Authorization: Bearer SEU_TOKEN_AQUI

## 📄 Documentação da API
Acesse a documentação gerada pelo Swagger:
http://localhost:8080/swagger-ui/index.html

Desenvolvido por Larissa Rocha 
