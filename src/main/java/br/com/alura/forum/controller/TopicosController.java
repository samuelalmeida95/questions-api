package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.DetalhesTopicoDTO;
import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/topicos")
public class TopicosController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping("/{idTopico}")
    @ResponseStatus(HttpStatus.OK)
    public DetalhesTopicoDTO buscarPorId(@PathVariable Long  idTopico) {
         Topico topico = topicoService.findById(idTopico);
         return new DetalhesTopicoDTO(topico);
    }

    @GetMapping(value = "/nomeTitulo")
    @ResponseStatus(HttpStatus.OK)
    public DetalhesTopicoDTO buscarPorTitulo(@RequestParam String titulo) {
        Topico tituloTpico = topicoService.findByTitulo(titulo);
        return new DetalhesTopicoDTO(tituloTpico);
    }

    @GetMapping("/listarTopicos")
    @ResponseStatus(HttpStatus.OK)
    public List<TopicoDTO> listarTodos () {

        List<Topico> listTopicos = topicoService.findAll();

        return listTopicos
                .stream()
                .map(TopicoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/listarTopicosPeloCurso")
    @ResponseStatus(HttpStatus.OK)
    public List<TopicoDTO> listarTodos (@RequestParam String nomeCurso) {

        List<Topico> listTopicos = topicoService.findAllByNomeCurso(nomeCurso);

        return listTopicos
                .stream()
                .map(TopicoDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> cadastrarTopico(
            @RequestBody @Valid TopicoForm form,
            UriComponentsBuilder uriBuilder) {

        Topico topico = topicoService.cadastrar(form);

        URI uri = uriBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @PutMapping(value = "atualizar/{idTopico}")
    public ResponseEntity<TopicoDTO> atualizarTopico(
            @PathVariable Long idTopico, 
            @RequestBody @Valid AtualizacaoTopicoForm form){

        Topico topico = topicoService.atualizar(idTopico, form);

        return ResponseEntity.ok().body(new TopicoDTO(topico));
    }

    @DeleteMapping(value = "/{idTopico}")
    public ResponseEntity<?> deletarTopico(@PathVariable Long idTopico){
        topicoService.deletar(idTopico);
        return ResponseEntity.noContent().build();
    }
}
