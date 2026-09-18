package com.estudo.biblioteca.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.estudo.biblioteca.model.Livro;
import com.estudo.biblioteca.model.StatusLivro;
import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
    List<Livro> findByStatus(StatusLivro status);
    
}