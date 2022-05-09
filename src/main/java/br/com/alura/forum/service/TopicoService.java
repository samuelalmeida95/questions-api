package br.com.alura.forum.service;

import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico inexistente"));
    }

    public Topico findByTitulo(String titulo) {
        return topicoRepository
                .findByTitulo(titulo)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Título inexistente"));
    }

    public List<Topico> findAll() {
        List<Topico> topicos = topicoRepository.findAll();

        if(topicos.isEmpty())
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existem Tópicos");

        return topicos;
    }

    public List<Topico> findAllByNomeCurso(String nomeCurso) {
        List<Topico> cursos = topicoRepository.findByCursoNome(nomeCurso);

        if (cursos.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso inexistente!");

        return cursos;
    }

    public Topico cadastrar(TopicoForm form) {
        Topico topico = form.converter(cursoRepository);
        return topicoRepository.save(topico);
    }

    public Topico atualizar(Long idTopico, AtualizacaoTopicoForm form) {
        Topico topicoParaAtualizar = findById(idTopico);
        atualizaTopico(form, topicoParaAtualizar);
        return topicoRepository.save(topicoParaAtualizar);
    }

    private void atualizaTopico(AtualizacaoTopicoForm form, Topico topicoParaAtualizar) {
        topicoParaAtualizar.setTitulo(form.getTitulo());
        topicoParaAtualizar.setMensagem(form.getMensagem());
    }

    public void deletar(Long idTopico) {
        topicoRepository.deleteById(idTopico);
    }
}
