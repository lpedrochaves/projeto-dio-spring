package com.clinica.medica.api.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.clinica.medica.api.model.Especialidade;
import com.clinica.medica.api.model.Medico;

public class EspecialidadeGetDTO {
	private UUID id;
	private String tipoEspecialidade;
	private List<MedicoEspecialidadeDTO> medicos;

	public EspecialidadeGetDTO() {

	}

	public EspecialidadeGetDTO(Especialidade e) {
		this.id = e.getId();
		this.tipoEspecialidade = e.getTipoEspecialidade();
		List<Medico> medicos = new ArrayList<>(e.getMedico());
		List<MedicoEspecialidadeDTO> medicosDTO = new ArrayList<>();
		for (Medico med : medicos) {
			medicosDTO.add(new MedicoEspecialidadeDTO(med));
		}
		this.medicos = medicosDTO;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTipoEspecialidade() {
		return tipoEspecialidade;
	}

	public void setTipoEspecialidade(String tipoEspecialidade) {
		this.tipoEspecialidade = tipoEspecialidade;
	}

	public List<MedicoEspecialidadeDTO> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<MedicoEspecialidadeDTO> medicos) {
		this.medicos = medicos;
	}

}
