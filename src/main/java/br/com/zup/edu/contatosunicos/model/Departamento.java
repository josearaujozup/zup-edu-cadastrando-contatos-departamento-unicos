package br.com.zup.edu.contatosunicos.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departamento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String nome;
	
	@Column(nullable = false, unique=true)
    private String sigla;
	
	@OneToMany(mappedBy = "departamento", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<Contato> contatos = new HashSet<>();

	public Departamento(String nome, String sigla) {
		this.nome = nome;
		this.sigla = sigla;
	}
	
	/**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
    public Departamento() {
    	
    }
    
    public Long getId() {
		return id;
	}

	public void adicionar(Contato contato) {
		contato.setDepartamento(this);
		contatos.add(contato);
	}
}
