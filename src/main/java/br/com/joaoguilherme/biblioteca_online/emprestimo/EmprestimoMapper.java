package br.com.joaoguilherme.biblioteca_online.emprestimo;

import org.springframework.stereotype.Service;

@Service
public class EmprestimoMapper {
    public EmprestimoResponse toEmprestimoResponse(Emprestimo emprestimo) {
        return new EmprestimoResponse(
                emprestimo.getId(),
                emprestimo.getUsuario().getId(),
                emprestimo.getLivro().getId(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataPrevistaDevolucao(),
                emprestimo.getStatus());
    }
}
