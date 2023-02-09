package com.clinica.medica.api.dto;

import java.util.UUID;

import com.clinica.medica.api.model.Endereco;
import com.clinica.medica.api.model.Medico;

public class MedicoInserirDTO {
	private UUID id;
	private String nome;
	private String email;
	private String telefone;
	private String cpf;
	private String crm;
	private Boolean ativo;
	private Endereco endereco;
	private EspecialidadeInserirDTO especialidade;

	public MedicoInserirDTO() {

	}

	public MedicoInserirDTO(Medico m) {
		this.id = m.getId();
		this.nome = m.getNome();
		this.email = m.getEmail();
		this.telefone = m.getTelefone();
		this.cpf = m.getCpf();
		this.crm = m.getCrm();
		this.ativo = m.getAtivo();
	
		this.especialidade = new EspecialidadeInserirDTO(m.getEspecialidade());
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public EspecialidadeInserirDTO getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(EspecialidadeInserirDTO especialidade) {
		this.especialidade = especialidade;
	}

}