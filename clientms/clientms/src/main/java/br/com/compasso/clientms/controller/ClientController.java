package br.com.compasso.clientms.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.clientms.dto.ClientDTO;
import br.com.compasso.clientms.exception.CityNotFoundException;
import br.com.compasso.clientms.exception.ClientAlreadyExistsException;
import br.com.compasso.clientms.exceptionhandler.ClientExceptionHandler.Error;
import br.com.compasso.clientms.model.Client;
import br.com.compasso.clientms.service.ClientService;


@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	ClientService clientService;

	@PostMapping("/new")
	public ResponseEntity<Client> newClient(@Valid @RequestBody (required = true) ClientDTO clientDTO) {
		Client newClient = clientService.save(clientDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
	}

	@GetMapping("/{id}")
	public Client findClientById(@PathVariable Long id) {
		return clientService.findById(id);
	}

	@GetMapping(params = "name")
	public Client findClientByName(@RequestParam(required = true) String name) {
		return clientService.findClientByName(name);
	}
	
	@PutMapping("/{id}")
	public void updateClient(@PathVariable Long id, @RequestBody Client client) {
		clientService.updateClient(id, client);
	}

	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable Long id) {
		clientService.deleteClient(id);
	}
	
	@ExceptionHandler({ ClientAlreadyExistsException.class })
	public ResponseEntity<Object> handleClientAlreadyExistsException(ClientAlreadyExistsException ex) {
		String userMessage = "This client is already registered.";
		String developerMessage = ex.toString();
		List<Error> errors = Arrays.asList(new Error(developerMessage, userMessage));
		return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler({ CityNotFoundException.class })
	public ResponseEntity<Object> handleCityNotFoundException(CityNotFoundException ex) {
		String userMessage = "No cities found with this name.";
		String developerMessage = ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
		return ResponseEntity.badRequest().body(errors);
	}

}
