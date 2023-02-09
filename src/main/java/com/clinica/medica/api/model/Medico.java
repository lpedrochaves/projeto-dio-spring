package com.clinica.medica.api.model;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "medico")
public class Medico {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_medico", columnDefinition = "uuid")
	private UUID id;
	@Column(name = "nome", nullable = false, length = 80)
	private String nome;
	@Column(name = "email", nullable = false, length = 80)
	private String email;
	@Column(name = "celular", nullable = false, length = 12)
	private String telefone;
	@Column(name = "cpf", nullable = false, length = 11)
	private String cpf;
	@Column(name = "crm", nullable = false)
	private String crm;
	@Column(name = "ativo", nullable = false)
	private Boolean ativo = true;
	// @OneToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "id_endereco")
	//@JsonIgnore
	@Embedded
	private Endereco endereco;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_especialidade")
	private Especialidade especialidade;

	public Medico() {

	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

}
