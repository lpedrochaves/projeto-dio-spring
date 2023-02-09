package com.clinica.medica.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.medica.api.model.Medico;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, UUID>{

	Optional<Medico> findById(UUID id);
	
	Medico findByCpf(String cpf);
	
	List<Medico> findAll();

	Medico findByCrm(String crm);
}
