package com.clinica.medica.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.medica.api.model.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, UUID>{

	Especialidade findByTipoEspecialidade(String tipoEspecialidade);
	
	Optional<Especialidade> findById(UUID id);

}
