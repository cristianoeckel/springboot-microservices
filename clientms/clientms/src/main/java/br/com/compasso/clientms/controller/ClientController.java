package br.com.compasso.clientms.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("clients")
public class ClientController {

	@Autowired
	ClientService clientService;

	@ApiOperation(value = "Register a new customer.")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDTO> createClient(
			@ApiParam(value = "New client data") @Valid @RequestBody(required = true) ClientDTO clientDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientDTO));
	}

	@ApiOperation(value = "Search client by id.", response = ClientDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDTO> getClientById(
			@ApiParam(value = "Client id", required = true) @PathVariable Long id) {
		return ResponseEntity.ok(clientService.findById(id));
	}

	@ApiOperation(value = "Search client by name.")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClientDTO>> getClientByName(
			@ApiParam(value = "Customer name", required = true) @RequestParam(required = true) String name) {
		return ResponseEntity.ok(clientService.findClientByName(name));
	}

	@ApiOperation(value = "Update client name.")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping(value = "{id}", consumes = MediaType.TEXT_PLAIN_VALUE)
	public void updateClientName(@ApiParam(value = "Client id.", example = "1", required = true) @PathVariable Long id,
			@RequestBody UpdateClientNameDTO updateClientNameDTO) {
		clientService.updateClientName(id, updateClientNameDTO);
	}

	@ApiOperation(value = "Delete an existing client by their id.")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@DeleteMapping("{id}")
	public void deleteClient(@ApiParam(value = "Client id", required = true) @PathVariable Long id) {
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
