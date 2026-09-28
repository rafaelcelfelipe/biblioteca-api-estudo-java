package com.estudo.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Autor(){}

    public Autor(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public Long getId(){
        return id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setId(Long id){
        this.id = id;
    }
}