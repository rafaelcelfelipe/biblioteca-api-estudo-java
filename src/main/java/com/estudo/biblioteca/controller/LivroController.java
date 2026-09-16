package com.estudo.biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudo.biblioteca.service.LivroService;
import com.estudo.biblioteca.model.Livro;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroService livroService;

    public LivroController(LivroService livroService){
        this.livroService = livroService;
    }

    @GetMapping()
    public List<Livro> listar(){
        return livroService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id){
        Optional<Livro> livro = livroService.buscarPorId(id);
        if (livro.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(livro.get());
    }

    @PostMapping()
    public Livro criar(@RequestBody Livro livro){
        return livroService.salvar(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        Optional<Livro> livro = livroService.buscarPorId(id);
        if (livro.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro livro){
        Optional<Livro> livroEditado = livroService.atualizar(id, livro);
        if (livroEditado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(livroEditado.get());
    }
}