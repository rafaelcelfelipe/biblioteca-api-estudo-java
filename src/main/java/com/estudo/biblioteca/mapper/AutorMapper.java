package com.estudo.biblioteca.mapper;


import com.estudo.biblioteca.model.Autor;
import com.estudo.biblioteca.dto.AutorRequest;
import com.estudo.biblioteca.dto.AutorResponse;

public class AutorMapper {
    public static Autor toEntity(AutorRequest autorRequest){
        return new Autor(autorRequest.getNome());
    }

    public static AutorResponse toResponse(Autor autor){
        return new AutorResponse(autor.getId(), autor.getNome());
    }
}