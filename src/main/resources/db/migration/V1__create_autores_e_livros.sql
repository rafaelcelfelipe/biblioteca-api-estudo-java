CREATE TABLE autores (
    id   BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);
CREATE TABLE livros (
    id       BIGSERIAL PRIMARY KEY,
    titulo   VARCHAR(255) NOT NULL,
    status   VARCHAR(32)  NOT NULL,
    autor_id BIGINT       NOT NULL REFERENCES autores (id)
);