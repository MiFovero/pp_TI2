# Exercício 2 - Integração Eclipse e PostgreSQL

## Sobre
Este repositório contém minha solução para o **Exercício 2: Integração Eclipse e PostgreSQL**, uma atividade prática individual da disciplina Trabalho Interdisciplinar 2 (TI2). O objetivo do exercício foi desenvolver um programa em Java utilizando a IDE Eclipse e integrar o projeto com o **SGBD PostgreSQL**, permitindo manipular dados de uma tabela através de operações CRUD.

---

## Implementação
Para esta tarefa, eu:

1. Criei a tabela `livros` no banco PostgreSQL com as colunas:
   - `id` (inteiro)  
   - `titulo` (texto)  
   - `autor` (texto)  
   - `status` (caractere: `'D'` para disponível, `'E'` para emprestado)  
2. Criei a classe `Livro.java` para representar a entidade `livros`.  
3. Criei a classe `DAO.java` para implementar as operações CRUD no banco de dados.  
4. Criei a classe `Principal.java`, que contém um **menu interativo** no console com as opções:
   - Listar livros  
   - Inserir livro  
   - Atualizar livro  
   - Excluir livro  
   - Sair do programa  
5. Foi incluído o arquivo **`pom.xml`** para gerenciamento do projeto via Maven, incluindo dependências e configuração do build.
   
O programa foi testado diretamente na IDE Eclipse para garantir a execução correta das funcionalidades.

---

## Como Executar
Para executar o programa:

1. Clone este repositório para sua máquina.  
2. Abra o projeto no Eclipse.  
3. Certifique-se de que o PostgreSQL está instalado e rodando, e que a tabela `livros` foi criada no banco `biblioteca`.  
4. Configure a conexão com o banco na classe `DAO.java` (host, porta, nome do banco, usuário e senha).  
5. Certifique-se de que o Maven está configurado para reconhecer o `pom.xml`.  
6. Execute a classe `Principal.java`.  
7. Utilize o menu para manipular os livros cadastrados (Listar, Inserir, Atualizar, Excluir, Sair).

---
## Recursos Utilizados

- IDE Eclipse  
- PostgreSQL  
- JDBC para conexão com o banco de dados  
- Material de apoio da disciplina:
  - Vídeo: Exemplo de conexão com PostgreSQL  
  - Documentação oficial do PostgreSQL: [https://www.postgresql.org/docs/](https://www.postgresql.org/docs/)

---

## Autoavaliação

Minha autoavaliação para este exercício foi de **05/05**, considerando a implementação completa das funcionalidades solicitadas, a organização do código, a correta integração com o banco PostgreSQL e a funcionalidade do menu interativo para acessar as operações CRUD.
