package com.clinica.medica.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.medica.Exception.EspecialidadeException;
import com.clinica.medica.api.dto.EspecialidadeGetDTO;
import com.clinica.medica.api.dto.EspecialidadeInserirDTO;
import com.clinica.medica.api.model.Especialidade;
import com.clinica.medica.api.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	public List<EspecialidadeGetDTO> getAll() {
		List<Especialidade> especs = especialidadeRepository.findAll();
		List<EspecialidadeGetDTO> especsDTO = new ArrayList<>();
		for (Especialidade esp : especs) {
			especsDTO.add(new EspecialidadeGetDTO(esp));
		}
		return especsDTO;
	}

	public EspecialidadeInserirDTO getById(UUID id) {
		Optional<Especialidade> esp = especialidadeRepository.findById(id);

		if (!esp.isPresent()) {
			throw new EspecialidadeException("Especialidade não encontrada");
		}
		return  new EspecialidadeInserirDTO(esp.get());

	
	}

	public EspecialidadeInserirDTO cadastrar(EspecialidadeInserirDTO e) {
		Especialidade espec = especialidadeRepository.findByTipoEspecialidade(e.getTipoEspecialidade());
		if (espec != null) {
			throw new EspecialidadeException("Especialidade já cadastrada");
		}
		Especialidade esp = new Especialidade();
		esp.setTipoEspecialidade(e.getTipoEspecialidade());
		esp = especialidadeRepository.save(esp);
		EspecialidadeInserirDTO especDTO = new EspecialidadeInserirDTO(esp);
		return especDTO;
	}

	public Especialidade atualizar(UUID id, Especialidade e) {
		e.setId(id);
		if (!especialidadeRepository.findById(id).isPresent()) {
			throw new EspecialidadeException("Especialidade não encontrada ");
		}
		return especialidadeRepository.save(e);

	}

	public void deletar(UUID id) {
		if (!especialidadeRepository.findById(id).isPresent()) {
			throw new EspecialidadeException("Especialidade não encontrada!");
		}
		especialidadeRepository.deleteById(id);
	}
}
