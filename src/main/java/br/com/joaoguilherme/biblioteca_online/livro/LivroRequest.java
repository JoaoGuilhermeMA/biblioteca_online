package br.com.joaoguilherme.biblioteca_online.livro;

public record LivroRequest(
        String titulo,
        Integer autorId,
        Integer categoriaId,
        Integer anoPublicacao) {
}