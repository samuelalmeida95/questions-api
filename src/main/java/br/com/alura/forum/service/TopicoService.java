package br.com.alura.forum.service;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico findById(Long idTopico) {
        return topicoRepository
                .findById(idTopico)
                .orElseThrow(() -> new RuntimeException("topico inexistente"));
    }

    public Topico findByTitulo(String titulo) {
        return topicoRepository
                .findByTitulo(titulo)
                .orElseThrow(()-> new RuntimeException("titulo inexistente"));
    }

    public List<Topico> findAll() {
        return topicoRepository.findAll();
    }

    public List<Topico> findAllByNomeCurso(String nomeCurso) {
        List<Topico> cursos = topicoRepository.findByCursoNome(nomeCurso);

        if (cursos.isEmpty())
            throw new RuntimeException("Curso inexistente");

        return cursos;
    }
}
