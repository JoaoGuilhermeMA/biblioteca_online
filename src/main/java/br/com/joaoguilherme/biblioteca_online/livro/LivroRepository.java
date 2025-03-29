package br.com.joaoguilherme.biblioteca_online.livro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
    Optional<Livro> findByTituloIgnoreCase(String titulo);

    List<Livro> findByAutorId(Integer autorId);

    List<Livro> findByCategoriaId(Integer categoriaId);

    List<Livro> findByAnoPublicacao(Integer anoPublicacao);

    boolean existsByIdAndQuantidadeDisponivelGreaterThan(Integer id, int quantidade);

    Optional<Livro> findById(Integer id);

    @Modifying // Indica que é uma operação de UPDATE/DELETE
    @Query("UPDATE Livro l SET l.quantidadeDisponivel = l.quantidadeDisponivel - 1 WHERE l.id = :id AND l.quantidadeDisponivel > 0")
    int decrementarQuantidade(@Param("id") Integer id);
}