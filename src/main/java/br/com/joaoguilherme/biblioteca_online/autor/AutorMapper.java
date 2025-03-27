package br.com.joaoguilherme.biblioteca_online.autor;

import org.springframework.stereotype.Service;

@Service
public class AutorMapper {

    /**
     * converter um autor para um autor response
     * 
     * @param autor autor a ser convertido
     * @return autor response a ser devolvido
     */
    public AutorResponse toAutorResponse(Autor autor) {
        return new AutorResponse(
                autor.getId(),
                autor.getNome(),
                autor.getBiografia());
    }

    public Autor toAutor(AutorRequest autorRequest) {
        return new Autor(
                autorRequest.nome(),
                autorRequest.biografia());
    }

    public void updateAutorFromRequest(AutorRequest request, Autor entity) {
        entity.setNome(request.nome());
        entity.setBiografia(request.biografia());
        ;
    }
}
