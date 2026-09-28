package com.estudo.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudo.biblioteca.service.LivroService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import com.estudo.biblioteca.model.StatusLivro;
import com.estudo.biblioteca.dto.LivroResponse;
import com.estudo.biblioteca.dto.LivroRequest;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroService livroService;

    public LivroController(LivroService livroService){
        this.livroService = livroService;
    }

    @GetMapping()
    public List<LivroResponse> listar(@RequestParam(required = false) StatusLivro status){
        return livroService.listar(status);
    }

    @GetMapping("/{id}")
    public LivroResponse buscarPorId(@PathVariable Long id){
        return livroService.buscarPorId(id);
    }

    @PostMapping()
    public LivroResponse criar(@Valid @RequestBody LivroRequest livro){
        return livroService.salvar(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public LivroResponse atualizar(@PathVariable Long id, @Valid @RequestBody LivroRequest livro){
        return livroService.atualizar(id, livro);
    }
}