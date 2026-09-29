package com.estudo.biblioteca.service;


import org.springframework.stereotype.Service;
import com.estudo.biblioteca.repository.LivroRepository;
import com.estudo.biblioteca.model.Livro;
import java.util.List;
import com.estudo.biblioteca.model.StatusLivro;
import com.estudo.biblioteca.exception.RecursoNaoEncontradoException;
import com.estudo.biblioteca.mapper.LivroMapper;
import com.estudo.biblioteca.dto.LivroResponse;
import com.estudo.biblioteca.dto.LivroRequest;
import com.estudo.biblioteca.model.Autor;
import com.estudo.biblioteca.repository.AutorRepository;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public List<LivroResponse> listar(StatusLivro status){
        List<Livro> livros;
        if (status == null){
            livros = livroRepository.findAll();
        } else {
            livros = livroRepository.findByStatus(status);
        }
        return livros.stream().map(LivroMapper::toResponse).toList();
    }

    public LivroResponse buscarPorId(Long id){
        return LivroMapper.toResponse(buscarEntidade(id));
    }

    public LivroResponse salvar(LivroRequest livroRequest){
        return LivroMapper.toResponse(livroRepository.save(LivroMapper.toEntity(livroRequest, buscarAutor(livroRequest.getAutorId()))));
    }

    public void deletar(Long id){
        buscarEntidade(id);
        livroRepository.deleteById(id);
    }

    public LivroResponse atualizar(Long id, LivroRequest livroRequest){
        Livro livroExistente = buscarEntidade(id);

        livroExistente.setTitulo(livroRequest.getTitulo());
        livroExistente.setAutor(buscarAutor(livroRequest.getAutorId()));
        livroExistente.setStatus(livroRequest.getStatus());

        return LivroMapper.toResponse(livroRepository.save(livroExistente));
    }

    private Livro buscarEntidade(Long id){
        return livroRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Livro não encontrado com id: " + id));
    }

    private Autor buscarAutor(Long id){
        return autorRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Autor não encontrado com id: " + id));
    }
}