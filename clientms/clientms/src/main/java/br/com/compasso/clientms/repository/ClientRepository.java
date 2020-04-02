package br.com.compasso.clientms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compasso.clientms.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	List<Client> findByName(String name);

}