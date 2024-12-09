# Projeto ToDoList - API de Tarefas

Este projeto é uma aplicação web que permite aos usuários realizar o registro, login e gerenciamento de tarefas, incluindo funcionalidades para criar, visualizar, editar, excluir e filtrar tarefas. Ele se comunica com uma API REST para gerenciar usuários e suas tarefas, além de realizar autenticação via JWT.

## Funcionalidades

- **Cadastro de Usuário**: Permite o registro de um novo usuário, incluindo email, nome de usuário e senha.
- **Login de Usuário**: Autenticação do usuário utilizando email e senha, com geração de token JWT para autenticação nas requisições seguintes.
- **Gerenciamento de Tarefas**: O usuário pode criar, editar e excluir tarefas, além de visualizá-las filtradas por status.
- **Histórico de Atividades**: Cada tarefa possui um histórico de atualizações, onde é possível ver as modificações realizadas.

## Requisitos

- **Java 21**
- **Spring Boot**
- **PostgreSQL**
- **JWT** para autenticação

## Configuração do Projeto

### Backend (API)

1. Clone o repositório:

    ```bash
    git clone <URL-do-repositório>
    cd <diretório-do-projeto>
    ```

2. Configure o banco de dados PostgreSQL:

    - Crie um banco de dados `todo_list`.
    - Configure o `application.properties` com as credenciais do banco de dados.

3. Compile e execute a API:

    ```bash
    ./mvnw spring-boot:run
    ```

A API estará disponível em `http://localhost:8000`.

### Frontend (Web)

O frontend foi construído utilizando **HTML**, **Bootstrap**, e **JavaScript puro**.

1. Abra os arquivos HTML no navegador (ou configure um servidor local, se necessário).

O sistema utiliza dois arquivos principais:

- `login.html` - Tela de login para autenticação do usuário.
- `homepage.html` - Página principal onde as tarefas são visualizadas e gerenciadas.

## Endpoints da API

### `POST /account/register` - Registro de usuário

- **Body**:

    ```json
    {
      "email": "string",
      "user": "string",
      "password": "string"
    }
    ```

### `POST /account/login` - Login de usuário

- **Body**:

    ```json
    {
      "email": "string",
      "password": "string"
    }
    ```

- **Resposta**:

    ```json
    {
      "token": "jwt-token"
    }
    ```

### `POST /tasks` - Criação de tarefa

- **Body**:

    ```json
    {
      "title": "string",
      "description": "string",
      "priority": 1
    }
    ```

- **Resposta**:

    ```json
    {
      "id": "task-id",
      "title": "string",
      "description": "string",
      "priority": "1",
      "state": "UNFULFILLED"
    }
    ```

## Considerações Finais

Devido a responsabilidades de estágio, este projeto não foi finalizado em sua totalidade, mas ele cobre as funcionalidades principais de cadastro, login e gerenciamento de tarefas com autenticação JWT. A aplicação está funcionando corretamente para os casos descritos e está pronta para ser expandida conforme necessário.
