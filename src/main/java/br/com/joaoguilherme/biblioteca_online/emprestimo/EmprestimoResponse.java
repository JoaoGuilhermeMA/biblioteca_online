package br.com.joaoguilherme.biblioteca_online.emprestimo;

import java.time.LocalDate;

public record EmprestimoResponse(
                Integer id,
                Integer usuarioId,
                Integer livroId,
                LocalDate dataEmprestimo,
                LocalDate dataPrevistaDevolucao,
                String status) {
}