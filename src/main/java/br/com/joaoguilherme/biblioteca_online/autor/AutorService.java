package br.com.joaoguilherme.biblioteca_online.autor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;
    @Autowired
    private AutorMapper mapper;

    /**
     * O findAll() busca todos os autores no banco de dados
     * O stream() converte a lista em stream para processamento funcional
     * O map(mapper::toAutorResponse) para cada elemento do stream (para cada
     * autor), aplica o metodo toAutorResponse do mapper
     * O collect(Collectors.toList()) transforma o stream em uma lista de volta
     * 
     * @return retorna uma lista de autores que estão no banco de dados na forma de
     *         AutorResponse
     */
    public List<AutorResponse> listarAutores() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toAutorResponse)
                .collect(Collectors.toList());
    }

    /**
     * o findByNomeIgnoreCase busca o autor pelo nome ignorando maiusculas e
     * minusculas
     * o .findFirst() pega o primeiro autor que encontrar,
     * o orElseThrow lança uma exceção caso não encontre nenhum autor
     * 
     * @param nome nome do autor a ser buscado
     * @return retorna um AutorResponse com o autor encontrado
     */
    public AutorResponse buscaAutorPeloNome(String nome) {
        return repository.findByNomeIgnoreCase(nome)
                .stream()
                .map(mapper::toAutorResponse)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
    }

    /**
     * Salva um autor no banco de dados
     * repository.save salva o autor no banco de dados
     * mapper.toAutor converte o AutorRequest em um Autor
     * mapper.toAutorResponse converte o Autor em um AutorResponse
     * 
     * @param autorRequest autor a ser salvo
     * @return retorna o autor salvo na forma de AutorResponse
     */
    public AutorResponse criarAutor(AutorRequest autorRequest) {
        Autor autor = repository.save(mapper.toAutor(autorRequest));
        return mapper.toAutorResponse(autor);
    }

    /**
     * atualiza um autor no banco de dados
     * se o autor não existir, lança uma exceção
     * repository.save salva o autor no banco de dados
     * mapper.toAutor converte o AutorRequest em um Autor
     * 
     * @param autorRequest
     * @param id
     * @return
     */
    public AutorResponse atualizarAutor(AutorRequest autorRequest, Integer id) {
        // 1. Busca o autor existente
        Autor autorExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        // 2. Atualiza os campos do autor existente
        mapper.updateAutorFromRequest(autorRequest, autorExistente);

        // 3. Salva o autor atualizado
        Autor autorAtualizado = repository.save(autorExistente);

        return mapper.toAutorResponse(autorAtualizado);
    }

    public void deletarAutor(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Autor não encontrado");
        }
        repository.deleteById(id);
    }
}
