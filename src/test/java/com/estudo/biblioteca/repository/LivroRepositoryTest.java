package com.estudo.biblioteca.repository;

import com.estudo.biblioteca.model.Autor;
import com.estudo.biblioteca.model.Livro;
import com.estudo.biblioteca.model.StatusLivro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Test
    void findByStatusRetornaApenasLivrosComAqueleStatus() {
        Autor autor = autorRepository.save(new Autor("Tolkien"));
        livroRepository.save(new Livro("O Hobbit", autor, StatusLivro.LENDO));
        livroRepository.save(new Livro("Duna", autor, StatusLivro.LIDO));

        List<Livro> lidos = livroRepository.findByStatus(StatusLivro.LIDO);

        assertEquals(1, lidos.size());
        assertEquals("Duna", lidos.get(0).getTitulo());
        assertEquals(StatusLivro.LIDO, lidos.get(0).getStatus());
    }

    @Test
    void findByStatusRetornaListaVaziaQuandoNaoHaCorrespondencia() {
        Autor autor = autorRepository.save(new Autor("Tolkien"));
        livroRepository.save(new Livro("O Hobbit", autor, StatusLivro.LENDO));

        List<Livro> lidos = livroRepository.findByStatus(StatusLivro.LIDO);

        assertTrue(lidos.isEmpty());
    }
}
