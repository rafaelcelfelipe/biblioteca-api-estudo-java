package com.estudo.biblioteca.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.estudo.biblioteca.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
    
}