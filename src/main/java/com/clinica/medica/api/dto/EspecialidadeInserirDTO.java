package com.clinica.medica.api.dto;

import java.util.UUID;

import com.clinica.medica.api.model.Especialidade;

import jakarta.validation.constraints.NotBlank;

public class EspecialidadeInserirDTO {
	private UUID id;
	@NotBlank(message = "Campo em branco, por favor digite uma especialidade.")
	private String tipoEspecialidade;

	public EspecialidadeInserirDTO(Especialidade e) {
		this.id = e.getId();
		this.tipoEspecialidade = e.getTipoEspecialidade();
	}
	
	public EspecialidadeInserirDTO() {
		
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
	

}
