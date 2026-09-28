package com.estudo.biblioteca.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveCriarAutor() throws Exception {
        mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"J.R.R. Tolkien\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("J.R.R. Tolkien"))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void deveRetornar400QuandoNomeVazio() throws Exception {
        mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveBuscarAutorPorId() throws Exception {
        String corpo = mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Tolkien\"}"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer id = JsonPath.read(corpo, "$.id");

        mockMvc.perform(get("/autores/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("Tolkien"));
    }

    @Test
    public void deveDeletarAutor() throws Exception {
        String corpo = mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Autor para apagar\"}"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer id = JsonPath.read(corpo, "$.id");

        mockMvc.perform(delete("/autores/" + id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/autores/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornar404AoDeletarAutorInexistente() throws Exception {
        mockMvc.perform(delete("/autores/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveAtualizarAutor() throws Exception {
        String corpo = mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Tolkien\"}"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer id = JsonPath.read(corpo, "$.id");

        mockMvc.perform(put("/autores/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"J.R.R. Tolkien\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("J.R.R. Tolkien"));
    }

    @Test
    public void deveRetornar404AoAtualizarAutorInexistente() throws Exception {
        mockMvc.perform(put("/autores/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Ninguém\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveRetornar400AoAtualizarComNomeVazio() throws Exception {
        String corpo = mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Tolkien\"}"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer id = JsonPath.read(corpo, "$.id");

        mockMvc.perform(put("/autores/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"\"}"))
                .andExpect(status().isBadRequest());
    }
}
