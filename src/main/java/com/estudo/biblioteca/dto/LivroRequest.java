package com.estudo.biblioteca.dto;

import com.estudo.biblioteca.model.StatusLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LivroRequest {
    @NotBlank(message = "Título é obrigatório")
    private String titulo;
    @NotNull(message = "Id do autor é obrigatório")
    private Long autorId;
    @NotNull(message = "Status é obrigatório")
    private StatusLivro status;

    public String getTitulo() {
        return titulo;
    }

    public Long getAutorId() {
        return autorId;
    }
    
    public StatusLivro getStatus() {
        return status;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }
    
    public void setStatus(StatusLivro status) {
        this.status = status;
    }
}