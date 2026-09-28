package com.estudo.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estudo.biblioteca.model.Autor;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

}