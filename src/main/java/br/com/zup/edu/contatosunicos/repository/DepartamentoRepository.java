package br.com.zup.edu.contatosunicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.edu.contatosunicos.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{

	public boolean existsBySigla(String sigla);

}
