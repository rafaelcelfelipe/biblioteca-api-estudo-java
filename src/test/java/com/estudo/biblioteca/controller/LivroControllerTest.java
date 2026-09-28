package com.estudo.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class LivroControllerTest{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveCriarLivro() throws Exception {
        mockMvc.perform(post("/livros").contentType(MediaType.APPLICATION_JSON)
.content("{\"titulo\":\"O Hobbit\",\"autor\":\"Tolkien\",\"status\":\"QUERO_LER\"}")).andExpect(jsonPath("$.titulo").value("O Hobbit")).andExpect(jsonPath("$.id").exists());
    }
}