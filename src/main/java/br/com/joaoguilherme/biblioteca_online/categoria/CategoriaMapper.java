package br.com.joaoguilherme.biblioteca_online.categoria;

import org.springframework.stereotype.Service;

@Service
public class CategoriaMapper {
    public CategoriaResponse toCategoriaResponse(Categoria Categoria) {
        return new CategoriaResponse(
                Categoria.getId(),
                Categoria.getNome());
    }

    public Categoria toCategoria(CategoriaRequest CategoriaRequest) {
        return new Categoria(
                CategoriaRequest.nome());
    }

    public void updateCategoriaFromRequest(CategoriaRequest request, Categoria entity) {
        entity.setNome(request.nome());
    }
}
