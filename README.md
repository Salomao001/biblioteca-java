# Biblioteca de Livros - Sistema Cliente-Servidor

Este projeto é uma aplicação cliente-servidor para gerenciar o registro/cadastro de livros de uma biblioteca. A comunicação entre o cliente e o servidor é feita por meio de sockets, e os dados dos livros são armazenados em um arquivo JSON.

# Desenvolvedores
  - Eduardo Salomão 
  - Gabriel Ribeiro Noronha
  - Cecilia Cortes

## Funcionalidades

1. **Listagem dos livros**
2. **Aluguel de livros**
3. **Devolução de livros**
4. **Cadastro de novos livros**

## Estrutura do Projeto

O projeto está organizado nas seguintes pastas e arquivos principais:

```
src/
├── com/br/biblioteca/functions/
│   ├── ClienteFunctions.java
│   ├── ServidorFunctions.java
├── com/br/biblioteca/menu/
│   └── Menu.java
├── com/br/biblioteca/model/
│   └── Livro.java
├── com/br/biblioteca/socket/
│   ├── ClienteSocket.java
│   ├── ClientHandler.java
│   └── ServidorSocket.java
├── livros.json
```

### Descrição das Classes

- **Livro** (`com.br.biblioteca.model.Livro`): Representa um livro na biblioteca.
- **ServidorSocket** (`com.br.biblioteca.socket.ServidorSocket`): Inicia o servidor que escuta conexões na porta 12345.
- **ClientHandler** (`com.br.biblioteca.socket.ClientHandler`): Gerencia as requisições dos clientes.
- **ServidorFunctions** (`com.br.biblioteca.functions.ServidorFunctions`): Realiza operações como listar, alugar, devolver e cadastrar livros.
- **ClienteSocket** (`com.br.biblioteca.socket.ClienteSocket`): Inicia o cliente que se conecta ao servidor.
- **ClienteFunctions** (`com.br.biblioteca.functions.ClienteFunctions`): Solicita operações ao servidor e exibe resultados.
- **Menu** (`com.br.biblioteca.menu.Menu`): Exibe o menu de opções para o usuário.

## Como Executar

### Executar o Servidor

1. Compile o projeto.
2. Execute a classe `ServidorSocket`.
3. Execute a classe `ClienteSocket`.


### Funcionalidades

1. **Listar livros**:
   - Selecionar a opção 1 no menu do cliente.
   - O cliente envia a solicitação ao servidor, que retorna a lista de livros.

2. **Alugar livro**:
   - Selecionar a opção 2 no menu do cliente.
   - Inserir o título e autor do livro.
   - O cliente envia a solicitação ao servidor, que processa o aluguel e retorna a resposta.

3. **Devolver livro**:
   - Selecionar a opção 3 no menu do cliente.
   - Inserir o título e autor do livro.
   - O cliente envia a solicitação ao servidor, que processa a devolução e retorna a resposta.

4. **Cadastrar livro**:
   - Selecionar a opção 4 no menu do cliente.
   - Inserir os detalhes do livro (título, autor, gênero, exemplares).
   - O cliente envia a solicitação ao servidor, que processa o cadastro e retorna a resposta.

## Estrutura do Arquivo JSON

O arquivo `livros.json` armazena os dados dos livros e possui a seguinte estrutura:

```json
{
  "livros": [
    {
      "titulo": "Meditações",
      "autor": "Marco Aurélio",
      "genero": "Filosofia",
      "exemplares": 4
    },
    {
      "titulo": "Orgulho e Preconceito",
      "autor": "Jane Austen",
      "genero": "Romance",
      "exemplares": 4
    }
  ]
}
```

## Requisitos

- **Java 17**
- **Biblioteca Gson** para manipulação do arquivo JSON.

## Instalação das Dependências

Para instalar a biblioteca Gson, adicione a seguinte dependência ao seu projeto Maven:

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.8</version>
</dependency>
```

---

### Observação

Certifique-se de que o arquivo `livros.json` esteja no diretório `src` do projeto para que o servidor possa acessá-lo corretamente.

