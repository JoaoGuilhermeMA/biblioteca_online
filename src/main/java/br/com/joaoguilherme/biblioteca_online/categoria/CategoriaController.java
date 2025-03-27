package br.com.joaoguilherme.biblioteca_online.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponse> criarCategoria(@RequestBody CategoriaRequest categoriaRequest) {
        return ResponseEntity.ok(categoriaService.criarCategoria(categoriaRequest));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CategoriaResponse>> buscarCategoriaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> atualizarCategoria(@PathVariable Integer id,
            @RequestBody CategoriaRequest categoriaRequest) {
        return ResponseEntity.ok(categoriaService.atualizarCategoria(id, categoriaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
