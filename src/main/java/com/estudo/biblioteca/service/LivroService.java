package com.estudo.biblioteca.service;


import org.springframework.stereotype.Service;
import com.estudo.biblioteca.repository.LivroRepository;
import com.estudo.biblioteca.model.Livro;
import java.util.List;
import com.estudo.biblioteca.model.StatusLivro;
import com.estudo.biblioteca.exception.RecursoNaoEncontradoException;

@Service
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> listar(StatusLivro status){
        if (status == null){
            return livroRepository.findAll();
        }
        return livroRepository.findByStatus(status);
    }

    public Livro buscarPorId(Long id){
        return livroRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Livro não encontrado com id: " + id));
    }

    public Livro salvar(Livro livro){
        return livroRepository.save(livro);
    }

    public void deletar(Long id){
        buscarPorId(id);
        livroRepository.deleteById(id);
    }

    public Livro atualizar(Long id, Livro livro){
        Livro livroExistente = buscarPorId(id);

        livroExistente.setTitulo(livro.getTitulo());
        livroExistente.setAutor(livro.getAutor());
        livroExistente.setStatus(livro.getStatus());

        return salvar(livroExistente);
    }
}