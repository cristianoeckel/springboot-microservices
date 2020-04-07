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
import br.com.compasso.clientms.dto.UpdateClientNameDTO;
import br.com.compasso.clientms.exception.CityNotFoundException;
import br.com.compasso.clientms.exceptionhandler.ClientExceptionHandler.Error;
import br.com.compasso.clientms.service.ClientService;


@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	ClientService clientService;

	@PostMapping("/new")
	public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody (required = true) ClientDTO clientDTO) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(clientService.save(clientDTO));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
		return ResponseEntity.ok(clientService.findById(id));
	}

	@GetMapping(params = "name")
	public ResponseEntity<ClientDTO> getClientByName(@RequestParam(required = true) String name) {
		return ResponseEntity.ok(clientService.findClientByName(name));
	}
	
	@PutMapping("/{id}")
	public void updateClientName(@PathVariable Long id, @RequestBody UpdateClientNameDTO updateClientNameDTO) {
		clientService.updateClientName(id, updateClientNameDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable Long id) {
		clientService.deleteClient(id);
	}
	
	@ExceptionHandler({ CityNotFoundException.class })
	public ResponseEntity<Object> handleCityNotFoundException(CityNotFoundException ex) {
		String userMessage = "No cities found with this name.";
		String developerMessage = ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
		return ResponseEntity.badRequest().body(errors);
	}

}
