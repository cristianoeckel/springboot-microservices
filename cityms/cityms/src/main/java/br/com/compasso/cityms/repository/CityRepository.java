package br.com.compasso.cityms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.compasso.cityms.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
	
	public Optional<City> findByNameIgnoreCase(String name);

	public Optional<List<City>> findByStateIgnoreCase(String state);
	
}
