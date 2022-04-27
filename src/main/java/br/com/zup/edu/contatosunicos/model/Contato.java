package br.com.zup.edu.contatosunicos.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Table(uniqueConstraints = {
		@UniqueConstraint(name = "UK_telefone_departamento", columnNames = {"telefone", "departamento_id"})
})
@Entity
public class Contato {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String telefone;
	
	@Column(nullable = false)
    private String nomeFuncionario;
	
	@Column(nullable = false)
    private LocalDate dataCadastro;
	
	@ManyToOne(optional = false)
    private Departamento departamento;

	public Contato(String telefone, String nomeFuncionario, LocalDate dataCadastro) {
		this.telefone = telefone;
		this.nomeFuncionario = nomeFuncionario;
		this.dataCadastro = dataCadastro;
	}
	
	/**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
	public Contato() {
		
	}
	
    public Long getId() {
		return id;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
    
    
}
