package com.estudo.biblioteca.mapper;


import com.estudo.biblioteca.model.Livro;
import com.estudo.biblioteca.dto.LivroRequest;
import com.estudo.biblioteca.dto.LivroResponse;

public class LivroMapper {
    public static Livro toEntity(LivroRequest livroRequest){
        return new Livro(livroRequest.getTitulo(), livroRequest.getAutor(), livroRequest.getStatus());
    }

    public static LivroResponse toResponse(Livro livro){
        return new LivroResponse(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getStatus());
    }
}