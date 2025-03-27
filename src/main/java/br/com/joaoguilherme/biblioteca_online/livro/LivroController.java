package br.com.joaoguilherme.biblioteca_online.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroResponse>> listarLivros() {
        return ResponseEntity.ok(livroService.listarLivros());
    }

    @PostMapping
    public ResponseEntity<LivroResponse> criarLivro(@RequestBody @Valid LivroRequest livroRequest) {
        return ResponseEntity.ok(livroService.criarLivro(livroRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> editarLivro(@PathVariable("id") Integer id,
            @RequestBody @Valid LivroRequest livroRequest) {
        return ResponseEntity.ok(livroService.editarLivro(id, livroRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable("id") Integer id) {
        livroService.deletarLivro(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<LivroResponse> buscarPorTitulo(@PathVariable("titulo") String titulo) {
        return ResponseEntity.ok(livroService.buscarLivroPorTitulo(titulo));
    }

    @GetMapping("/autor/{autorId}")
    public ResponseEntity<List<LivroResponse>> buscarPorAutor(@PathVariable("autorId") Integer autorId) {
        return ResponseEntity.ok(livroService.buscarLivrosPorAutor(autorId));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<LivroResponse>> buscarPorCategoria(@PathVariable("categoriaId") Integer categoriaId) {
        return ResponseEntity.ok(livroService.buscarLivrosPorCategoria(categoriaId));
    }

    @GetMapping("/ano/{anoPublicacao}")
    public ResponseEntity<List<LivroResponse>> buscarPorAnoPublicacao(
            @PathVariable("anoPublicacao") Integer anoPublicacao) {
        return ResponseEntity.ok(livroService.buscarLivrosPorAnoPublicacao(anoPublicacao));
    }
}
