package br.com.joaoguilherme.biblioteca_online.livro;

import br.com.joaoguilherme.biblioteca_online.autor.Autor;
import br.com.joaoguilherme.biblioteca_online.categoria.Categoria;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class LivroMapper {
    public LivroResponse toLivroResponse(Livro livro) {
        return new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor().getNome(),
                livro.getCategoria().getNome(),
                livro.getAnoPublicacao(),
                livro.getQuantidadeDisponivel());
    }

    public Livro toLivro(LivroRequest livroRequest, Autor autor, Categoria categoria) {
        return new Livro(
                null,
                livroRequest.titulo(),
                autor,
                categoria,
                livroRequest.anoPublicacao(),
                livroRequest.quantidadeDisponivel());
    }

    public Livro toLivro(LivroResponse livroResponse, Autor autor, Categoria categoria) {
        return new Livro(
                null,
                livroResponse.titulo(),
                autor,
                categoria,
                livroResponse.anoPublicacao(),
                livroResponse.quantidadeDisponivel());
    }
}
