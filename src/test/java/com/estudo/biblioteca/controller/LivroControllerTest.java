package com.estudo.biblioteca.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveCriarLivro() throws Exception {
        Integer autorId = criarAutor("Tolkien");

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"O Hobbit\",\"autorId\":" + autorId + ",\"status\":\"QUERO_LER\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.titulo").value("O Hobbit"))
                .andExpect(jsonPath("$.status").value("QUERO_LER"))
                .andExpect(jsonPath("$.autor.id").value(autorId))
                .andExpect(jsonPath("$.autor.nome").value("Tolkien"));
    }

    @Test
    public void deveRetornar404QuandoAutorNaoExiste() throws Exception {
        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"O Hobbit\",\"autorId\":9999,\"status\":\"QUERO_LER\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornar400QuandoTituloVazio() throws Exception {
        Integer autorId = criarAutor("Tolkien");

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"\",\"autorId\":" + autorId + ",\"status\":\"QUERO_LER\"}"))
                .andExpect(status().isBadRequest());
    }

    private Integer criarAutor(String nome) throws Exception {
        String corpo = mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"" + nome + "\"}"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return JsonPath.read(corpo, "$.id");
    }
}
