package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/topicos")
public class TopicosController {

    @GetMapping("/listarTopicos")
    public List<TopicoDTO> listar() {
        Topico t1 = new Topico("Duvida", "Duvida Spring", new Curso("Spring", "Programação"));
        return TopicoDTO.converter(Arrays.asList(t1, t1, t1, t1));
    }


}
