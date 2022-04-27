package br.com.zup.edu.contatosunicos.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.edu.contatosunicos.model.Contato;
import br.com.zup.edu.contatosunicos.model.Departamento;
import br.com.zup.edu.contatosunicos.repository.ContatoRepository;
import br.com.zup.edu.contatosunicos.repository.DepartamentoRepository;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	private final DepartamentoRepository repository;
	private final ContatoRepository contatoRepository;
	
	public DepartamentoController(DepartamentoRepository repository, ContatoRepository contatoRepository) {
		this.repository = repository;
		this.contatoRepository = contatoRepository;
	}
	
	
	@PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DepartamentoDTO request, UriComponentsBuilder uriComponentsBuilder){
		
		if(repository.existsBySigla(request.getSigla())) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Ingresso já existe no sistema");
		}
		
        Departamento departamento = request.toModel();

        repository.save(departamento);

        URI location = uriComponentsBuilder.path("/departamentos/{id}")
                .buildAndExpand(departamento.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
	
	@PostMapping("/{idDepartamento}/contatos")
    public ResponseEntity<?> cadastrarContato(@RequestBody @Valid ContatoDTO request, @PathVariable("idDepartamento") Long idDepartamento, UriComponentsBuilder uriComponentsBuilder){
		
		Departamento departamento = repository.findById(idDepartamento).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Departamento não existe"));
		
		if(contatoRepository.existsByTelefoneAndDepartamentoId(request.getTelefone(),departamento.getId())) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Contato com esse telefone existente para esse departamento");
		}
		
        Contato contato = request.toModel();
        departamento.adicionar(contato);
        
        repository.flush();
//        contatoRepository.save(contato);

        URI location = uriComponentsBuilder.path("/{idDepartamento}/contatos/{idContato}")
                .buildAndExpand(departamento.getId(),contato.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
	
	
	@ExceptionHandler
	public ResponseEntity<?> handleUniqueConstraintErrors(ConstraintViolationException e){
		
		
		Map<String, Object> body = Map.of(
				"message", "entidade já existente no sistema",
				"timestamp", LocalDateTime.now()
		);
		
		return ResponseEntity.unprocessableEntity().body(body);
	}
	
	
	
}
