package com.estudo.biblioteca.dto;

import com.estudo.biblioteca.model.StatusLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LivroRequest {
    @NotBlank(message = "Título é obrigatório")
    private String titulo;
    @NotBlank(message = "Autor é obrigatório")
    private String autor;
    @NotNull(message = "Status é obrigatório")
    private StatusLivro status;

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
    
    public StatusLivro getStatus() {
        return status;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public void setStatus(StatusLivro status) {
        this.status = status;
    }
}