package com.estudo.biblioteca.mapper;

import com.estudo.biblioteca.dto.LivroRequest;
import com.estudo.biblioteca.dto.LivroResponse;
import com.estudo.biblioteca.model.Livro;
import com.estudo.biblioteca.model.StatusLivro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LivroMapperTest {

    @Test
    void toEntityCopiaCamposDoRequestSemId() {
        LivroRequest request = request("O Hobbit", "Tolkien", StatusLivro.QUERO_LER);

        Livro livro = LivroMapper.toEntity(request);

        assertNull(livro.getId());
        assertEquals("O Hobbit", livro.getTitulo());
        assertEquals("Tolkien", livro.getAutor());
        assertEquals(StatusLivro.QUERO_LER, livro.getStatus());
    }

    @Test
    void toResponseCopiaIdECamposDaEntidade() {
        Livro livro = new Livro("Duna", "Herbert", StatusLivro.LENDO);
        livro.setId(7L);

        LivroResponse response = LivroMapper.toResponse(livro);

        assertEquals(7L, response.getId());
        assertEquals("Duna", response.getTitulo());
        assertEquals("Herbert", response.getAutor());
        assertEquals(StatusLivro.LENDO, response.getStatus());
    }

    private static LivroRequest request(String titulo, String autor, StatusLivro status) {
        LivroRequest request = new LivroRequest();
        request.setTitulo(titulo);
        request.setAutor(autor);
        request.setStatus(status);
        return request;
    }
}
