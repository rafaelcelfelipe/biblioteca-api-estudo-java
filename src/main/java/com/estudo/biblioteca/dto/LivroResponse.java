package com.estudo.biblioteca.dto;

import com.estudo.biblioteca.model.StatusLivro;

public class LivroResponse {
    private Long id;
    private String titulo;
    private AutorResponse autor;
    private StatusLivro status;

    public LivroResponse(Long id, String titulo, AutorResponse autor, StatusLivro status) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public AutorResponse getAutor() {
        return autor;
    }

    public StatusLivro getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(AutorResponse autor) {
        this.autor = autor;
    }
    
    public void setStatus(StatusLivro status) {
        this.status = status;
    }
}