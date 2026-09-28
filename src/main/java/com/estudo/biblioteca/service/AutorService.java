package com.estudo.biblioteca.service;

import org.springframework.stereotype.Service;
import com.estudo.biblioteca.repository.AutorRepository;
import com.estudo.biblioteca.dto.AutorRequest;
import com.estudo.biblioteca.dto.AutorResponse;
import com.estudo.biblioteca.mapper.AutorMapper;
import com.estudo.biblioteca.model.Autor;
import com.estudo.biblioteca.exception.RecursoNaoEncontradoException;
import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    public AutorResponse salvar(AutorRequest autorRequest){
        return AutorMapper.toResponse(autorRepository.save(AutorMapper.toEntity(autorRequest)));   
    }

    public List<AutorResponse> listar(){
        return autorRepository.findAll().stream().map(AutorMapper::toResponse).toList();
    }

    public AutorResponse buscarPorId(Long id){
        return AutorMapper.toResponse(buscarEntidade(id));
    }

    private Autor buscarEntidade(Long id){
        return autorRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Autor não encontrado com o id: " + id));
    }
}