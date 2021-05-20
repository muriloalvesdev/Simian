package br.com.simian.domain.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.simian.domain.model.entity.DNA;

public interface DNARepository extends JpaRepository<DNA, UUID> {
	Optional<DNA> findByDna(String[] dna);
}
