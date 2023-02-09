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
import com.clinica.medica.Exception.MedicoException;
import com.clinica.medica.api.dto.MedicoAtualizarDTO;
import com.clinica.medica.api.dto.MedicoInserirDTO;
import com.clinica.medica.api.model.Especialidade;
import com.clinica.medica.api.model.Medico;
import com.clinica.medica.api.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	@Autowired
	private MedicoService medicoService;

	@GetMapping
	public ResponseEntity<List<MedicoInserirDTO>> getAll() {
		return ResponseEntity.ok(medicoService.getAll());
	}

	@PostMapping("/cadastro-medico")
	public ResponseEntity<?> cadastrar(@RequestBody MedicoInserirDTO m) {
		try {
			MedicoInserirDTO medico = medicoService.cadastrar(m);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medico.getId())
					.toUri();
			return ResponseEntity.created(uri).body(medico);
		} catch (MedicoException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<?> atualizar(@PathVariable UUID id, @RequestBody MedicoAtualizarDTO m) {
		try {
			
			MedicoAtualizarDTO med = medicoService.atualizar(id, m);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(med.getId())
					.toUri();
			return ResponseEntity.created(uri).body(med);
		} catch (MedicoException ex) {
			return ResponseEntity.unprocessableEntity().body(ex.getMessage());
		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteLogical(@PathVariable UUID id) {
		try {
			medicoService.deleteLogical(id);
			return ResponseEntity.noContent().build();
		} catch (MedicoException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@GetMapping("reativar/{id}")
	public ResponseEntity<?> reativarMedico(@PathVariable UUID id) {
		try {
			String medico = medicoService.reativarMedico(id);
			return ResponseEntity.ok(medico);
		} catch (EspecialidadeException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
}
