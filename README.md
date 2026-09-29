# API Biblioteca

API REST para cadastro de **autores** e **livros** (biblioteca pessoal). Cada livro pertence a um autor e tem um status de leitura: `QUERO_LER`, `LENDO` ou `LIDO`.

## Sobre este repositório

Este é um **projeto de estudo de Java e Spring Boot**. O objetivo foi aprender na prática: camadas, JPA, testes, HTTP e o fluxo de uma API de verdade.

Foi desenvolvido **com apoio de IA** (orientação, revisão de erros e alguns testes/configurações). O código de domínio — entities, DTOs, services, controllers e o raciocínio das camadas — foi **escrito cerca de 90% à mão**, passo a passo, sem gerar a aplicação pronta de uma vez.

Não é um produto. É material de aprendizado.

## O que a API faz

- CRUD de autores (`/autores`)
- CRUD de livros (`/livros`)
- Livro ligado a autor (`autorId` na entrada; autor completo na resposta)
- Filtro de livros por status (`GET /livros?status=LIDO`)
- Validação de entrada (título, nome, status, `autorId`)
- 404 quando o recurso não existe
- Documentação interativa (Swagger UI)

## Stack

| Peça | Tecnologia |
|------|------------|
| Linguagem | Java 17 |
| Framework | Spring Boot 4.1 |
| API HTTP | Spring Web MVC |
| Persistência | Spring Data JPA / Hibernate |
| Banco (local / estudo) | H2 em arquivo (`./data/biblioteca`) |
| Banco (opcional) | PostgreSQL + Flyway |
| Validação | Bean Validation (`@Valid`, `@NotBlank`, `@NotNull`) |
| Documentação | springdoc-openapi / Swagger UI |
| Testes | JUnit 5, MockMvc, Mockito, `@DataJpaTest` |
| Build | Maven Wrapper (`./mvnw`) |
| CI | GitHub Actions |

## Técnicas e padrões

- **Camadas:** Controller → Service → Repository
- **DTOs + Mapper:** a API não expõe a entity JPA
- **Injeção por construtor**
- **`@ManyToOne`:** muitos livros, um autor (`autor_id`)
- **Queries derivadas:** `findByStatus`
- **Exceção de domínio + `@RestControllerAdvice`:** 404 centralizado
- **`ResponseEntity`:** 204 no DELETE
- **TDD**
- **Testes de integração** (MockMvc + H2) e **unitários** (Service com mock, Mapper)
- **Perfis:** H2 por padrão; Postgres com `-Dspring-boot.run.profiles=postgres`

## Como rodar

Java 17. Na pasta do projeto:

```bash
./mvnw spring-boot:run
```

- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs
- Console H2: http://localhost:8080/h2-console  
  JDBC URL: `jdbc:h2:file:./data/biblioteca`

O H2 em arquivo **mantém os dados** depois de parar a API.

### Testes

```bash
./mvnw test
```

Os testes usam H2 em memória (`src/test/resources/application.properties`) e não dependem do arquivo local.

### PostgreSQL (opcional)

Há `docker-compose.yml`, migração Flyway (`db/migration/V1__...sql`) e `application-postgres.properties`. Só use se tiver Postgres em `localhost:5432` (usuário/senha/banco `biblioteca`):

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres
```

## Endpoints (resumo)

**Autores**

| Método | Rota | Efeito |
|--------|------|--------|
| GET | `/autores` | Listar |
| GET | `/autores/{id}` | Buscar |
| POST | `/autores` | Criar `{ "nome": "Tolkien" }` |
| PUT | `/autores/{id}` | Atualizar |
| DELETE | `/autores/{id}` | Remover (204) |

**Livros**

| Método | Rota | Efeito |
|--------|------|--------|
| GET | `/livros` | Listar (opcional `?status=LENDO`) |
| GET | `/livros/{id}` | Buscar |
| POST | `/livros` | Criar `{ "titulo", "autorId", "status" }` |
| PUT | `/livros/{id}` | Atualizar |
| DELETE | `/livros/{id}` | Remover (204) |

Não é possível apagar um autor que ainda tenha livros (restrição de FK).

## Estrutura do código

```
com.estudo.biblioteca
├── controller
├── service
├── repository
├── model
├── dto
├── mapper
├── exception
└── config          # OpenAPI
```
