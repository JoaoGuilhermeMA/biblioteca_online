package br.com.joaoguilherme.biblioteca_online.livro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
    Optional<Livro> findByTituloIgnoreCase(String titulo);

    List<Livro> findByAutorId(Integer autorId);

    List<Livro> findByCategoriaId(Integer categoriaId);

    List<Livro> findByAnoPublicacao(Integer anoPublicacao);
}