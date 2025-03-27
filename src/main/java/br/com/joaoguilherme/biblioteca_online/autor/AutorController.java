package br.com.joaoguilherme.biblioteca_online.autor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/autor")
class AutorController {

    @Autowired
    private AutorService service;

    @GetMapping
    public ResponseEntity<List<AutorResponse>> listarAutores() {
        return ResponseEntity.ok(service.listarAutores());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<AutorResponse> buscaAutorPeloNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.buscaAutorPeloNome(nome));
    }

    @PostMapping
    public ResponseEntity<AutorResponse> criarAutor(@RequestBody @Valid AutorRequest autorRequest) {
        return ResponseEntity.ok(service.criarAutor(autorRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable("id") Integer id) {
        service.deletarAutor(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponse> atualizarAutor(@PathVariable Integer id,
            @RequestBody @Valid AutorRequest autorRequest) {
        return ResponseEntity.ok(service.atualizarAutor(autorRequest, id));
    }

}