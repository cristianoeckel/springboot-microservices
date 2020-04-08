package br.com.compasso.cityms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

/**
 * Controller for the requests related to cities.
 * 
 * @author cristiano.eckel
 *
 */
@RestController
@RequestMapping("/v1/cities")
@Api(value = "City", tags = "City")
public class CityController {

	@Autowired
	private CityService cityService;

	@ApiOperation(value = "Register a new city")
	@PostMapping
	public ResponseEntity<CityDTO> registerCity(
			@ApiParam(value = "CityDTO Object") @Valid @RequestBody(required = true) CityDTO cityDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(cityDTO));
	}

	@ApiOperation(value = "Search all the citys of this state")
	@GetMapping(params = "state")
	public ResponseEntity<List<CityDTO>> findByState(
			@ApiParam(value = "City state", required = true) @RequestParam String state) {
		return ResponseEntity.ok().body(cityService.findByState(state));
	}

	@ApiOperation(value = "Search for citys using the name parameter.")
	@GetMapping(params = "name")
	public ResponseEntity<List<CityDTO>> findByName(
			@ApiParam(value = "City name", required = true) @RequestParam String name) {
		return ResponseEntity.ok().body(cityService.findByName(name));

	}
}
