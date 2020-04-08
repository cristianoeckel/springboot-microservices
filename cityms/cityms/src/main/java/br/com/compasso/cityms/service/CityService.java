package br.com.compasso.cityms.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.compasso.cityms.DTO.CityDTO;
import br.com.compasso.cityms.model.City;
import br.com.compasso.cityms.repository.CityRepository;

import org.modelmapper.ModelMapper;


@Service
public class CityService {
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	ModelMapper modelMapper;

	public CityDTO save(@RequestBody(required = true) CityDTO cityDTO) {
		City city = modelMapper.map(cityDTO, City.class);
		cityRepository.save(city);
	    cityDTO.setId(city.getId());
		return cityDTO;
	}

	public List<CityDTO> findByState(String state) {
		List<City> cities = findCityByState(state);
		List<CityDTO> citiesDTO = Arrays.asList(modelMapper.map(cities, CityDTO[].class));
		return citiesDTO;
	}
	
	public List<CityDTO> findByName(String name) {
		List<City> cities = findByCityName(name);
		List<CityDTO> citiesDTO = Arrays.asList(modelMapper.map(cities, CityDTO[].class));
		return citiesDTO;
	}

	private List<City>findByCityName(String name) {
		return cityRepository.findByNameIgnoreCase(name)
				.orElseThrow(() -> new EmptyResultDataAccessException("No cities found with this name", 1));
	}
	
	private List<City> findCityByState(String state) {
		return cityRepository.findByStateIgnoreCase(state)
				.orElseThrow(() -> new EmptyResultDataAccessException("No client found with this NAME", 1));
	}
	
}
