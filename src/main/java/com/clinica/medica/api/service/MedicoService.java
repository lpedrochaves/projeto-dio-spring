package com.clinica.medica.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.medica.Exception.MedicoException;
import com.clinica.medica.api.dto.MedicoAtualizarDTO;
import com.clinica.medica.api.dto.MedicoInserirDTO;
import com.clinica.medica.api.model.Especialidade;
import com.clinica.medica.api.model.Medico;
import com.clinica.medica.api.repository.EspecialidadeRepository;
import com.clinica.medica.api.repository.MedicoRepository;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private EspecialidadeRepository especialidadeRepository;

//	private MedicoInserirDTO convertToObjectDto(Medico m) {
//
//		EspecialidadeInserirDTO espDTO = new EspecialidadeInserirDTO();
//		Especialidade esp = new Especialidade();
//		espDTO.setId(esp.getId());
//		espDTO.setTipoEspecialidade(esp.getTipoEspecialidade());
//		
//
//		MedicoInserirDTO medico = new MedicoInserirDTO();
//		medico.setId(m.getId());
//		medico.setNome(m.getNome());
//		medico.setEmail(m.getEmail());
//		medico.setTelefone(m.getTelefone());
//		medico.setCpf(m.getCpf());
//		medico.setCrm(m.getCrm());
//		medico.setAtivo(true);
//		medico.setEndereco(m.getEndereco());
//		medico.setEspecialidade(espDTO);
//		return medico;
//	}

	public List<MedicoInserirDTO> getAll() {
		List<Medico> medicos = medicoRepository.findAll();
		List<MedicoInserirDTO> medicosDTO = new ArrayList<>();
		for (Medico medico : medicos) {
			medicosDTO.add(new MedicoInserirDTO(medico));
		}
		return medicosDTO;
	}

	public MedicoInserirDTO cadastrar(MedicoInserirDTO m) {
		Medico med2 = medicoRepository.findByCrm(m.getCpf());

		Optional<Especialidade> esp = especialidadeRepository.findById(m.getEspecialidade().getId());
		if (medicoRepository.findByCpf(m.getCpf()) != null) {
			throw new MedicoException("Médico já cadastrado");
		}
		if (medicoRepository.findByCrm(m.getCrm()) != null) {
			throw new MedicoException("Médico já cadastrado");
		}

		Medico medico = new Medico();
		medico.setNome(m.getNome());
		medico.setEmail(m.getEmail());
		medico.setTelefone(m.getTelefone());
		medico.setCpf(m.getCpf());
		medico.setCrm(m.getCrm());
		medico.setAtivo(true);
		medico.setEndereco(m.getEndereco());
		medico.setEspecialidade(esp.get());

		medico = medicoRepository.save(medico);
		return new MedicoInserirDTO(medico);
	}

	public MedicoAtualizarDTO atualizar(UUID id, MedicoAtualizarDTO m) {
		m.setId(id);
		if (!medicoRepository.findById(id).isPresent()) {
			throw new MedicoException("Médico não encontrado");
		}

		Optional<Medico> med = medicoRepository.findById(id);
		Medico medico = new Medico();
		medico.setId(id);
		medico.setAtivo(true);
		medico.setCpf(med.get().getCpf());
		medico.setCrm(med.get().getCrm());
		medico.setEspecialidade(med.get().getEspecialidade());
		medico.setNome(m.getNome());
		medico.setEmail(m.getEmail());
		medico.setTelefone(m.getTelefone());
		medico.setEndereco(m.getEndereco());
		medico = medicoRepository.save(medico);

		return new MedicoAtualizarDTO(medico);
	}

	public void deleteLogical(UUID id) {
		Optional<Medico> medico = medicoRepository.findById(id);
		if (!medico.isPresent()) {
			throw new MedicoException("Médico não encontrado");
		}
		if (medico.get().getAtivo() == false) {
			throw new MedicoException("Médico não encontrado");
		}
		Medico med = medico.get();
		med.setAtivo(false);
		medicoRepository.save(med);
	}

	public String reativarMedico(UUID id) {
		Optional<Medico> medico = medicoRepository.findById(id);
		if (!medico.isPresent()) {
			throw new MedicoException("Médico não encontrado");
		}
		if (medico.get().getAtivo()) {
			throw new MedicoException("Médico já está ativo");
		}
		Medico med = medico.get();
		med.setAtivo(true);
		medicoRepository.save(med);

		return "Medico " + med.getNome() + " foi inserido no banco novamente.";
	}

}
