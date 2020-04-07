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

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityService cityService;

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
	}
}
