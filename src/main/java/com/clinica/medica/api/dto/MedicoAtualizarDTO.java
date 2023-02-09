package com.clinica.medica.api.dto;

import java.util.UUID;

import com.clinica.medica.api.model.Endereco;
import com.clinica.medica.api.model.Medico;

public class MedicoAtualizarDTO {
	private UUID id;
	private String nome;
	private String email;
	private String telefone;
	private Endereco endereco;

	public MedicoAtualizarDTO() {

	}

	public MedicoAtualizarDTO(Medico m) {
		this.nome = m.getNome();
		this.email = m.getEmail();
		this.telefone = m.getTelefone();
		this.endereco = m.getEndereco();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
