package com.estudo.biblioteca.mapper;


import com.estudo.biblioteca.model.Livro;
import com.estudo.biblioteca.dto.LivroRequest;
import com.estudo.biblioteca.dto.LivroResponse;
import com.estudo.biblioteca.model.Autor;
import com.estudo.biblioteca.mapper.AutorMapper;

public class LivroMapper {
        public static Livro toEntity(LivroRequest livroRequest, Autor autor){
            return new Livro(livroRequest.getTitulo(), autor, livroRequest.getStatus());
    }

    public static LivroResponse toResponse(Livro livro){
        return new LivroResponse(livro.getId(), livro.getTitulo(), AutorMapper.toResponse(livro.getAutor()), livro.getStatus());
    }
}