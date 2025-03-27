package br.com.joaoguilherme.biblioteca_online.livro;

public record LivroResponse(
        Integer id,
        String titulo,
        String autorNome,
        String categoriaNome,
        Integer anoPublicacao) {
}