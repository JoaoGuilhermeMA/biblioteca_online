package br.com.joaoguilherme.biblioteca_online.emprestimo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.joaoguilherme.biblioteca_online.livro.Livro;
import br.com.joaoguilherme.biblioteca_online.livro.LivroMapper;
import br.com.joaoguilherme.biblioteca_online.livro.LivroService;
import br.com.joaoguilherme.biblioteca_online.usuario.Usuario;
import br.com.joaoguilherme.biblioteca_online.usuario.UsuarioService;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroService livroService;
    private final LivroMapper livroMapper;
    private final EmprestimoMapper emprestimoMapper;
    private final UsuarioService usuarioService;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroService livroService,
            LivroMapper livroMapper, EmprestimoMapper emprestimoMapper, UsuarioService usuarioService) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroService = livroService;
        this.livroMapper = livroMapper;
        this.emprestimoMapper = emprestimoMapper;
        this.usuarioService = usuarioService;
    }

    public EmprestimoResponse realizarEmprestimo(Integer id, Integer usuarioId) {
        if (!emprestimoDisponivel(id)) {
            throw new Error("livro nao disponivel para emprestimo");
        }

        Usuario usuario = usuarioService.buscarUsuarioId(usuarioId)
                .orElseThrow(() -> new Error("Erro ao achar um usuario com esse id"));
        Livro livro = livroService.buscarLivroPorId(id)
                .orElseThrow(() -> new RuntimeException("livro não encontrado"));

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataPrevistaDevolucao(LocalDate.now().plusDays(14));
        emprestimo.setStatus("EM ANDAMENTO");
        return emprestimoMapper.toEmprestimoResponse(emprestimoRepository.save(emprestimo));
    }

    public boolean emprestimoDisponivel(Integer id) {
        return livroService.temLivroDisponivel(id);
    }

    public List<EmprestimoResponse> buscarTodosEmprestimos() {
        return emprestimoRepository.findAll().stream().map(emprestimoMapper::toEmprestimoResponse)
                .collect(Collectors.toList());
    }

    public void cancelarEmprestimo(Integer id) {
        emprestimoRepository.deleteById(id);
    }

}
