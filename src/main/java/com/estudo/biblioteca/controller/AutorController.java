package com.estudo.biblioteca.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.estudo.biblioteca.service.AutorService;
import com.estudo.biblioteca.dto.AutorRequest;
import com.estudo.biblioteca.dto.AutorResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/autores")
public class AutorController {
    private final AutorService autorService;

    public AutorController(AutorService autorService){
        this.autorService = autorService;
    }

    @PostMapping
    public AutorResponse salvar(@Valid @RequestBody AutorRequest autorRequest){
        return autorService.salvar(autorRequest);
    }

    @GetMapping
    public List<AutorResponse> listar() {
        return autorService.listar();
    }

    @GetMapping("/{id}")
    public AutorResponse buscarPorId(@PathVariable Long id){
        return autorService.buscarPorId(id);
    }
}