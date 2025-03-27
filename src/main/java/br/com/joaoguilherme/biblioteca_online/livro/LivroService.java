package br.com.joaoguilherme.biblioteca_online.livro;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaoguilherme.biblioteca_online.autor.Autor;
import br.com.joaoguilherme.biblioteca_online.autor.AutorRepository;
import br.com.joaoguilherme.biblioteca_online.categoria.Categoria;
import br.com.joaoguilherme.biblioteca_online.categoria.CategoriaRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroMapper livroMapper;

    public List<LivroResponse> listarLivros() {
        return livroRepository.findAll().stream()
                .map(livroMapper::toLivroResponse)
                .collect(Collectors.toList());
    }

    public LivroResponse criarLivro(LivroRequest livroRequest) {
        Autor autor = autorRepository.findById(livroRequest.autorId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        Categoria categoria = categoriaRepository.findById(livroRequest.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        Livro livro = livroMapper.toLivro(livroRequest, autor, categoria);
        return livroMapper.toLivroResponse(livroRepository.save(livro));
    }

    public LivroResponse editarLivro(Integer id, LivroRequest livroRequest) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Autor autor = autorRepository.findById(livroRequest.autorId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        Categoria categoria = categoriaRepository.findById(livroRequest.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        livroExistente.setTitulo(livroRequest.titulo());
        livroExistente.setAnoPublicacao(livroRequest.anoPublicacao());
        livroExistente.setAutor(autor);
        livroExistente.setCategoria(categoria);

        return livroMapper.toLivroResponse(livroRepository.save(livroExistente));
    }

    public void deletarLivro(Integer id) {
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado");
        }
        livroRepository.deleteById(id);
    }

    public LivroResponse buscarLivroPorTitulo(String titulo) {
        return livroRepository.findByTituloIgnoreCase(titulo)
                .map(livroMapper::toLivroResponse)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public List<LivroResponse> buscarLivrosPorAutor(Integer autorId) {
        return livroRepository.findByAutorId(autorId).stream()
                .map(livroMapper::toLivroResponse)
                .collect(Collectors.toList());
    }

    public List<LivroResponse> buscarLivrosPorCategoria(Integer categoriaId) {
        return livroRepository.findByCategoriaId(categoriaId).stream()
                .map(livroMapper::toLivroResponse)
                .collect(Collectors.toList());
    }

    public List<LivroResponse> buscarLivrosPorAnoPublicacao(Integer ano) {
        return livroRepository.findByAnoPublicacao(ano).stream()
                .map(livroMapper::toLivroResponse)
                .collect(Collectors.toList());
    }
}
