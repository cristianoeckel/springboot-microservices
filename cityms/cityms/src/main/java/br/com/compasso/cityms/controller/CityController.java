package br.com.compasso.cityms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.cityms.DTO.CityDTO;
import br.com.compasso.cityms.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



/**
 * Controller for the requests related to cities.
 * 
 * @author cristiano.eckel
 *
 */
@RestController
@RequestMapping("cities")
@Api(value = "City", tags = "City")
public class CityController {

	@Autowired
	private CityService cityService;

<<<<<<< 12e2f4cf2968aeab0f4c4fc003b6d71aa2cf938e
	@PostMapping
	public ResponseEntity<CityDTO> createClient(@Valid @RequestBody(required = true) CityDTO cityDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(cityDTO));
	}

	@GetMapping
	public ResponseEntity<List<CityDTO>> findByCityState(@RequestParam String state) {
		List<CityDTO> citiesDTO = cityService.findByState(state);
		if (!citiesDTO.isEmpty()) {
			return ResponseEntity.ok(citiesDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(params = "name")
	public ResponseEntity<List<CityDTO>> findByName(@RequestParam String name) {
		List<CityDTO> citiesDTO = cityService.findByName(name);
		if (!citiesDTO.isEmpty()) {
			return ResponseEntity.ok(citiesDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
=======
	@ApiOperation(value = "Register a new city")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CityDTO> registerCity(
			@ApiParam(value = "CityDTO Object") @Valid @RequestBody(required = true) CityDTO cityDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(cityDTO));
	}

	@ApiOperation(value = "Search all the citys of this state")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(params = "state", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CityDTO>> findByState(
			@ApiParam(value = "City state", required = true) @RequestParam String state) {
		return ResponseEntity.ok().body(cityService.findByState(state));
	}

	@ApiOperation(value = "Search for citys using the name parameter.")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(params = "name", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CityDTO>> findByName(
			@ApiParam(value = "City name", required = true) @RequestParam String name) {
		return ResponseEntity.ok().body(cityService.findByName(name));
>>>>>>> [ADD] Adicinando documentação com o swagger
	}
}
