package br.com.zup.edu.contatosunicos.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.zup.edu.contatosunicos.model.Departamento;

public class DepartamentoDTO {
	
	@NotBlank
	@Size(max = 120)
	private String nome;
	
	@NotBlank
    @Size(min = 1, max = 3)
    @Pattern(regexp = "^[A-Z]{1,3}$")
	private String sigla;

	public DepartamentoDTO(@NotBlank @Size(max = 120) String nome,
			@NotBlank @Size(min = 1, max = 3) @Pattern(regexp = "^[A-Z]{1,3}$") String sigla) {
		this.nome = nome;
		this.sigla = sigla;
	}
	
	public DepartamentoDTO() {
		
	}

	public String getNome() {
		return nome;
	}

	public String getSigla() {
		return sigla;
	}

	public Departamento toModel() {
		return new Departamento(nome, sigla);
	}
	
}
