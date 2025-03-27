package br.com.joaoguilherme.biblioteca_online.autor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
    List<Autor> findByNomeIgnoreCase(String nome); // busca o autor pelo nome ignorando maiusculas e minusculas

    Optional<Autor> findById(Integer id); // busca o autor pelo id
}
