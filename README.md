# Padrões de Projeto em uma Aplicação em Spring

A aplicação consiste em um gerenciamento de usuários utilizando operações CRUD com persistência em um banco de dados em memória (H2).

## Padrões Utilizados

### Controller
O padrão `Controller` foi aplicado na classe `UsuarioController` para expor endpoints REST que permitem operações CRUD. A API pode ser consumida de forma mais prática através da documentação gerada pelo Swagger.

### Model
O padrão `Model` é representado pela classe `Usuario`, que define a estrutura dos dados da entidade de mesmo nome no banco de dados. Essa classe permite o mapeamento relacional dos objetos para a persistência no banco.

### Repository
A interface `UsuarioRepository` segue o padrão `Repository`, sendo responsável por abstrair as operações de manipulação de dados ao estender `JpaRepository`. Isso elimina a necessidade de escrever consultas SQL manuais.

### Service
A classe `UsuarioService` implementa o padrão `Service`, encapsulando a lógica de negócio e servindo como uma camada de serviço que gerencia a interação com a interface `UsuarioRepository`.
