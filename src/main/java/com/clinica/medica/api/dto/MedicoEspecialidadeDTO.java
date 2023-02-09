package com.clinica.medica.api.dto;

import java.util.UUID;

import com.clinica.medica.api.model.Medico;

public class MedicoEspecialidadeDTO {
	private UUID id;
	private String nome;
	private String crm;

	public MedicoEspecialidadeDTO() {

	}

	public MedicoEspecialidadeDTO(Medico m) {
		this.id = m.getId();
		this.nome = m.getNome();
		this.crm = m.getCrm();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

}