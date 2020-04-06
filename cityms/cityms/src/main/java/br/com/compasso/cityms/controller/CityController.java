package br.com.compasso.cityms.controller;
import java.util.List;

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
import br.com.compasso.cityms.model.City;
import br.com.compasso.cityms.service.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@PostMapping(path = "/new")
	public ResponseEntity<City> newCity(@RequestBody (required = true) CityDTO cityDTO) {
		City newCity = cityService.save(cityDTO);
		return  ResponseEntity.status(HttpStatus.CREATED).body(newCity);
	}
	
	@GetMapping
	public ResponseEntity<List<City>> findByCityState(@RequestParam String state) {
		List<City> cities = cityService.findByState(state);
		if (!cities.isEmpty()) {
			return ResponseEntity.ok(cities);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(params = "name")
	public ResponseEntity<City> findByName(@RequestParam String name) {
		City city = cityService.findByName(name);	
		return ResponseEntity.ok(city);
	}
}
	

