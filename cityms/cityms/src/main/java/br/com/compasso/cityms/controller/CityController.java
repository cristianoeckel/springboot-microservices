package br.com.compasso.cityms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.cityms.model.City;
import br.com.compasso.cityms.repository.CityRepository;

@RestController
@RequestMapping("/citys")
public class CityController {
	
	@Autowired
	CityRepository cityRepository;
	
	@RequestMapping(path = "/new", method = RequestMethod.POST)
	public void newCity(@RequestBody (required = true) City city) {
		cityRepository.save(city);
	}
	
	@GetMapping
	public ResponseEntity<Page<City>> findByCityState(@RequestParam String cityState, Pageable pageable) {
		Page<City> cidades = cityRepository.findByCityState(cityState, pageable);
		if (!cidades.isEmpty()) {
			return ResponseEntity.ok(cidades);
		} else {
			
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(params = "cityName")
	public ResponseEntity<List<City>> findByCityName(@RequestParam String cityName) {
		List<City> cidades = cityRepository.findByCityName(cityName);
		if (!cidades.isEmpty()) {
			return ResponseEntity.ok(cidades);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
