package com.estudo.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Título é obrigatório")
    private String titulo;
    @NotBlank(message = "Autor é obrigatório")
    private String autor;
    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    private StatusLivro status;

    public Livro(){
    }

    public Livro(String titulo, String autor, StatusLivro status){
        this.titulo = titulo;
        this.autor = autor;
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public StatusLivro getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    public void setStatus(StatusLivro status){
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }
}