package br.com.joaoguilherme.biblioteca_online.emprestimo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/emprestimo")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping("/{id}/{usuarioId}")
    public ResponseEntity<EmprestimoResponse> fazerEmprestimo(@PathVariable("id") Integer id,
            @PathVariable("usuarioId") Integer usuarioId) {
        return ResponseEntity.ok(emprestimoService.realizarEmprestimo(id, usuarioId));
    }

    @GetMapping()
    public ResponseEntity<List<EmprestimoResponse>> buscarTodosEmprestimos() {
        return ResponseEntity.ok(emprestimoService.buscarTodosEmprestimos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarEmprestimo(@PathVariable("id") Integer id) {
        emprestimoService.cancelarEmprestimo(id);
        return ResponseEntity.accepted().build();
    }

}
