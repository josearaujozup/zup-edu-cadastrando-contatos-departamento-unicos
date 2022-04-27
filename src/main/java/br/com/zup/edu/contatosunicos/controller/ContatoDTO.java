package br.com.zup.edu.contatosunicos.controller;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.edu.contatosunicos.model.Contato;

public class ContatoDTO {
	
	@NotBlank
	@Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}$")
	private String telefone;
	
	@NotBlank
	private String nomeFuncionario;
	
	@NotNull
	@Past
    @JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	public ContatoDTO(@NotBlank @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}$") String telefone,
			@NotBlank String nomeFuncionario, @NotNull @Past LocalDate dataCadastro) {
		this.telefone = telefone;
		this.nomeFuncionario = nomeFuncionario;
		this.dataCadastro = dataCadastro;
	}

	public ContatoDTO() {
	}

	public String getTelefone() {
		return telefone;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public Contato toModel() {
		return new Contato(telefone, nomeFuncionario, dataCadastro);
	}
	
}
