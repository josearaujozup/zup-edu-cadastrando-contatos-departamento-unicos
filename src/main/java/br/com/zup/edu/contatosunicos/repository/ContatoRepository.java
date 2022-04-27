package br.com.zup.edu.contatosunicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.edu.contatosunicos.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

	public boolean existsByTelefoneAndDepartamentoId(String telefone, Long idDepartamento);

}
