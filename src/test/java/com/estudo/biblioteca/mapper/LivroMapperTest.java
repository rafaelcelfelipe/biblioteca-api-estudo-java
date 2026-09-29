package com.estudo.biblioteca.mapper;

import com.estudo.biblioteca.dto.LivroRequest;
import com.estudo.biblioteca.dto.LivroResponse;
import com.estudo.biblioteca.model.Autor;
import com.estudo.biblioteca.model.Livro;
import com.estudo.biblioteca.model.StatusLivro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LivroMapperTest {

    @Test
    void toEntityCopiaCamposDoRequestSemId() {
        Autor autor = autor(3L, "Tolkien");
        LivroRequest request = request("O Hobbit", 3L, StatusLivro.QUERO_LER);

        Livro livro = LivroMapper.toEntity(request, autor);

        assertNull(livro.getId());
        assertEquals("O Hobbit", livro.getTitulo());
        assertEquals(autor, livro.getAutor());
        assertEquals(StatusLivro.QUERO_LER, livro.getStatus());
    }

    @Test
    void toResponseCopiaIdECamposDaEntidade() {
        Autor autor = autor(3L, "Herbert");
        Livro livro = new Livro("Duna", autor, StatusLivro.LENDO);
        livro.setId(7L);

        LivroResponse response = LivroMapper.toResponse(livro);

        assertEquals(7L, response.getId());
        assertEquals("Duna", response.getTitulo());
        assertEquals(3L, response.getAutor().getId());
        assertEquals("Herbert", response.getAutor().getNome());
        assertEquals(StatusLivro.LENDO, response.getStatus());
    }

    private static Autor autor(Long id, String nome) {
        Autor autor = new Autor(nome);
        autor.setId(id);
        return autor;
    }

    private static LivroRequest request(String titulo, Long autorId, StatusLivro status) {
        LivroRequest request = new LivroRequest();
        request.setTitulo(titulo);
        request.setAutorId(autorId);
        request.setStatus(status);
        return request;
    }
}
