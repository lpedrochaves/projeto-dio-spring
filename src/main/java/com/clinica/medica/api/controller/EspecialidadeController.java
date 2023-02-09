package com.clinica.medica.api.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clinica.medica.Exception.EspecialidadeException;
import com.clinica.medica.api.dto.EspecialidadeGetDTO;
import com.clinica.medica.api.dto.EspecialidadeInserirDTO;
import com.clinica.medica.api.model.Especialidade;
import com.clinica.medica.api.service.EspecialidadeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("especialidades")
public class EspecialidadeController {

	@Autowired
	private EspecialidadeService especialidadeService;

	@GetMapping
//	@ApiOperation(value="Lista todas as especialidades", notes="Listagem de Especialidades")
//	@ApiResponses(value= {
//	@ApiResponse(code=200, message="Retorna todas as especialidades"),
//	@ApiResponse(code=401, message="Erro de autenticação"),
//	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
//	@ApiResponse(code=404, message="Recurso não encontrado"),
//	@ApiResponse(code=505, message="Exceção interna da aplicação"),
//	})
	public ResponseEntity<List<EspecialidadeGetDTO>> getAll() {
		return ResponseEntity.ok(especialidadeService.getAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<EspecialidadeInserirDTO> getById(@PathVariable UUID id){
		return ResponseEntity.ok(especialidadeService.getById(id));
	}
	
	@PostMapping("/cadastro-especialidade")
	public ResponseEntity<?> cadastrar(@RequestBody @Valid EspecialidadeInserirDTO ex) {
		try {
			EspecialidadeInserirDTO especs = especialidadeService.cadastrar(ex);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(especs.getId())
					.toUri();
			return ResponseEntity.created(uri).body(especs);
		} catch (EspecialidadeException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}

	@PutMapping("/atualizar/{id}")
	public ResponseEntity<?> atualizar(@PathVariable UUID id, @RequestBody Especialidade e) {
		try {
			Especialidade especs = especialidadeService.atualizar(id, e);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(especs.getId())
					.toUri();
			return ResponseEntity.created(uri).body(especs);
		} catch (EspecialidadeException ex) {
			return ResponseEntity.unprocessableEntity().body(ex.getMessage());
		}

	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<?> deletar(@PathVariable  UUID id) {
		try {
			especialidadeService.deletar(id);
			return ResponseEntity.noContent().build();
		} catch (EspecialidadeException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

}
