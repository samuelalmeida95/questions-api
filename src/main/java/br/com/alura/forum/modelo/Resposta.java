package br.com.alura.forum.modelo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Resposta {

	private Long id;
	private String mensagem;
	private Topico topico;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	private Usuario autor;
	private Boolean solucao = false;
}
