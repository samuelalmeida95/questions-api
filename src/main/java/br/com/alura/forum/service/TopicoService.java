package br.com.alura.forum.service;

import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

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

    public Topico cadastrar(TopicoForm form) {
        Topico topico = form.converter(cursoRepository);
        return topicoRepository.save(topico);
    }
}
