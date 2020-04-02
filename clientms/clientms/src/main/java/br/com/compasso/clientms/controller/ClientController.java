package br.com.compasso.clientms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.clientms.dto.ClientDTO;
import br.com.compasso.clientms.model.Client;
import br.com.compasso.clientms.repository.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	ClientRepository clientRepository;

	@PostMapping("/new")
	public void newClient(@RequestBody (required = true) Client client) {
		clientRepository.save(client);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> findClientById(@PathVariable Long id) {
		Optional<Client> client = clientRepository.findById(id);
		if (client.isPresent()) {
			return ResponseEntity.ok(new ClientDTO(client.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping(params = "name")
	public ResponseEntity<List<Client>> findClientByName(@RequestParam(required = true) String name) {
		List<Client> clients;
		clients = clientRepository.findByName(name);
		if (!clients.isEmpty()) {
			return ResponseEntity.ok(clients);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
		Optional<Client> optional = clientRepository.findById(id);
		if (optional.isPresent()) {
			ClientDTO newClient = new ClientDTO(client);
			client = newClient.updateClient(id, clientRepository);
			clientRepository.save(client);
			return ResponseEntity.ok(client);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable Long id) {
		Optional<Client> client = clientRepository.findById(id);
		if (client.isPresent()) {
			clientRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
