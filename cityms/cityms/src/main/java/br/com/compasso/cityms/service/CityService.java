package br.com.compasso.cityms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.compasso.cityms.DTO.CityDTO;
import br.com.compasso.cityms.exception.CityAlreadyExistsException;
import br.com.compasso.cityms.model.City;
import br.com.compasso.cityms.repository.CityRepository;

@Service
public class CityService {
	@Autowired
	CityRepository cityRepository;

	public City save(CityDTO cityDTO) {
		Optional<City> cityOpt = cityRepository.findByNameIgnoreCase(cityDTO.getName());
		if (cityOpt.isPresent()) {
			throw new CityAlreadyExistsException("This city already exists. ");
		}
		City city = City.builder().name(cityDTO.getName()).state(cityDTO.getState()).build();
		return cityRepository.save(city);
	}

	public List<City> findByState(String state) {
		return cityRepository.findByStateIgnoreCase(state)
				.orElseThrow(() -> new EmptyResultDataAccessException("No cities found for this STATE", 1));
	}

	public City findByName(String name) {
		return cityRepository.findByNameIgnoreCase(name)
				.orElseThrow(() -> new EmptyResultDataAccessException("No cities found with this name", 1));
	}
}
