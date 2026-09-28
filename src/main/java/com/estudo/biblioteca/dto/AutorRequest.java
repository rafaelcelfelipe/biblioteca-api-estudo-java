package com.estudo.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;

public class AutorRequest {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

}