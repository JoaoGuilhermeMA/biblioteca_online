package br.com.joaoguilherme.biblioteca_online.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CategoriaMapper categoriaMapper;

    public CategoriaResponse criarCategoria(CategoriaRequest categoriaRequest) {
        return categoriaMapper.toCategoriaResponse(
                categoriaRepository.save(categoriaMapper.toCategoria(categoriaRequest)));
    }

    public List<CategoriaResponse> listarCategorias() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toCategoriaResponse)
                .collect(Collectors.toList());
    }

    public Optional<CategoriaResponse> buscarCategoriaPorId(Integer id) {
        return categoriaRepository.findById(id)
                .map(categoriaMapper::toCategoriaResponse);
    }

    public CategoriaResponse atualizarCategoria(Integer id, CategoriaRequest categoriaRequest) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNome(categoriaRequest.nome());
            return categoriaMapper.toCategoriaResponse(categoriaRepository.save(categoria));
        }).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public void deletarCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
