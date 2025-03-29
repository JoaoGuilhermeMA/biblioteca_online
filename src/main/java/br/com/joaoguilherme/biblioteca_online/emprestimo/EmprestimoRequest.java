package br.com.joaoguilherme.biblioteca_online.emprestimo;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record EmprestimoRequest(
        @NotNull Integer usuarioId,
        @NotNull Integer livroId,
        @NotNull LocalDate dataEmprestimo,
        @NotNull LocalDate dataPrevistaDevolucao) {
}
