package com.estudo.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private StatusLivro status;

    public Livro(){
    }

    public Livro(String titulo, Autor autor, StatusLivro status){
        this.titulo = titulo;
        this.autor = autor;
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
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

    public void setAutor(Autor autor){
        this.autor = autor;
    }

    public void setStatus(StatusLivro status){
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }
}