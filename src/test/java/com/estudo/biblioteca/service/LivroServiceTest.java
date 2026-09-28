package com.estudo.biblioteca.service;

import com.estudo.biblioteca.dto.LivroRequest;
import com.estudo.biblioteca.dto.LivroResponse;
import com.estudo.biblioteca.exception.RecursoNaoEncontradoException;
import com.estudo.biblioteca.model.Livro;
import com.estudo.biblioteca.model.StatusLivro;
import com.estudo.biblioteca.repository.LivroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private LivroService livroService;

    @Test
    void listarSemStatusUsaFindAll() {
        when(livroRepository.findAll()).thenReturn(List.of(entidade(1L, "O Hobbit", StatusLivro.QUERO_LER)));

        List<LivroResponse> resultado = livroService.listar(null);

        assertEquals(1, resultado.size());
        assertEquals("O Hobbit", resultado.get(0).getTitulo());
        verify(livroRepository).findAll();
        verify(livroRepository, never()).findByStatus(any());
    }

    @Test
    void listarComStatusUsaFindByStatus() {
        when(livroRepository.findByStatus(StatusLivro.LIDO))
                .thenReturn(List.of(entidade(2L, "Duna", StatusLivro.LIDO)));

        List<LivroResponse> resultado = livroService.listar(StatusLivro.LIDO);

        assertEquals(1, resultado.size());
        assertEquals(StatusLivro.LIDO, resultado.get(0).getStatus());
        verify(livroRepository).findByStatus(StatusLivro.LIDO);
        verify(livroRepository, never()).findAll();
    }

    @Test
    void buscarPorIdRetornaResponseQuandoExiste() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(entidade(1L, "O Hobbit", StatusLivro.LENDO)));

        LivroResponse response = livroService.buscarPorId(1L);

        assertEquals(1L, response.getId());
        assertEquals("O Hobbit", response.getTitulo());
        assertEquals(StatusLivro.LENDO, response.getStatus());
    }

    @Test
    void buscarPorIdLancaExcecaoQuandoNaoExiste() {
        when(livroRepository.findById(99L)).thenReturn(Optional.empty());

        RecursoNaoEncontradoException ex = assertThrows(
                RecursoNaoEncontradoException.class,
                () -> livroService.buscarPorId(99L)
        );

        assertTrue(ex.getMessage().contains("99"));
    }

    @Test
    void salvarPersisteRequestEDevolveResponseComId() {
        when(livroRepository.save(any(Livro.class))).thenAnswer(invocation -> {
            Livro livro = invocation.getArgument(0);
            livro.setId(10L);
            return livro;
        });

        LivroResponse response = livroService.salvar(request("O Hobbit", "Tolkien", StatusLivro.QUERO_LER));

        assertEquals(10L, response.getId());
        assertEquals("O Hobbit", response.getTitulo());
        assertEquals("Tolkien", response.getAutor());
        assertEquals(StatusLivro.QUERO_LER, response.getStatus());
        verify(livroRepository).save(any(Livro.class));
    }

    @Test
    void atualizarAlteraCamposQuandoLivroExiste() {
        Livro existente = entidade(1L, "O Hobbit", StatusLivro.QUERO_LER);
        when(livroRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(livroRepository.save(existente)).thenReturn(existente);

        LivroResponse response = livroService.atualizar(1L, request("O Senhor dos Anéis", "Tolkien", StatusLivro.LENDO));

        assertEquals(1L, response.getId());
        assertEquals("O Senhor dos Anéis", response.getTitulo());
        assertEquals(StatusLivro.LENDO, response.getStatus());
        verify(livroRepository).save(existente);
    }

    @Test
    void atualizarLancaExcecaoQuandoLivroNaoExiste() {
        when(livroRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> livroService.atualizar(99L, request("X", "Y", StatusLivro.LIDO))
        );

        verify(livroRepository, never()).save(any());
    }

    @Test
    void deletarRemoveQuandoLivroExiste() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(entidade(1L, "O Hobbit", StatusLivro.LIDO)));

        livroService.deletar(1L);

        verify(livroRepository).deleteById(1L);
    }

    @Test
    void deletarLancaExcecaoENaoApagaQuandoLivroNaoExiste() {
        when(livroRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class, () -> livroService.deletar(99L));

        verify(livroRepository, never()).deleteById(any());
    }

    private static Livro entidade(Long id, String titulo, StatusLivro status) {
        Livro livro = new Livro(titulo, "Tolkien", status);
        livro.setId(id);
        return livro;
    }

    private static LivroRequest request(String titulo, String autor, StatusLivro status) {
        LivroRequest request = new LivroRequest();
        request.setTitulo(titulo);
        request.setAutor(autor);
        request.setStatus(status);
        return request;
    }
}
