package com.clinica.medica.api.model;

import java.util.List;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "especialidade")
public class Especialidade {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_especialidade", columnDefinition = "uuid")
	private UUID id;
	@Column(name = "tipoEspecialidade")
	private String tipoEspecialidade;
	@OneToMany(mappedBy = "especialidade")
	private List<Medico> medico;

	public Especialidade() {

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

	public List<Medico> getMedico() {
		return medico;
	}

	public void setMedico(List<Medico> medico) {
		this.medico = medico;
	}

}
