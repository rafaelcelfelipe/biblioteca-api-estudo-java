package com.estudo.biblioteca.service;


import org.springframework.stereotype.Service;
import com.estudo.biblioteca.repository.LivroRepository;
import com.estudo.biblioteca.model.Livro;
import java.util.List;
import java.util.Optional;
import com.estudo.biblioteca.model.StatusLivro;

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

    public Optional<Livro> buscarPorId(Long id){
        return livroRepository.findById(id);
    }

    public Livro salvar(Livro livro){
        return livroRepository.save(livro);
    }

    public void deletar(Long id){
        livroRepository.deleteById(id);
    }

    public Optional<Livro> atualizar(Long id, Livro livro){
        Optional<Livro> livroExistente = buscarPorId(id);
        if (livroExistente.isEmpty()){
            return Optional.empty();
        }

        Livro existente = livroExistente.get();

        existente.setTitulo(livro.getTitulo());
        existente.setAutor(livro.getAutor());
        existente.setStatus(livro.getStatus());

        return Optional.of(salvar(existente));
    }
}