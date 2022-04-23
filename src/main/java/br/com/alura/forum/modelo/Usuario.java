package br.com.alura.forum.modelo;

import lombok.Data;

@Data
public class Usuario {

	private Long id;
	private String nome;
	private String email;
	private String senha;
}
