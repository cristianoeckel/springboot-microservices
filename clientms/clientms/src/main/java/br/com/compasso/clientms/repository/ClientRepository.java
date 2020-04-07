package br.com.compasso.clientms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.clientms.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	Optional<List<Client>> findByNameIgnoreCase(String name);
	
	Optional<Client> findById(Long id);
}