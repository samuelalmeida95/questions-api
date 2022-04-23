package br.com.alura.forum.controller;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/topicos")
public class TopicosController {

    @GetMapping("/listarTopicos")
    @ResponseBody
    public List<Topico> listar() {
        Topico t1 = new Topico("Duvida", "Duvida Spring", new Curso("Spring", "Programação"));
        return Arrays.asList(t1, t1, t1, t1);
    }
}
