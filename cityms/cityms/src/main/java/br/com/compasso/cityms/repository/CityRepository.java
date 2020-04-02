package br.com.compasso.cityms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.compasso.cityms.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
	
	List<City> findByCityName(String cityName);

	Page<City> findByCityState(String cityState, Pageable pageable);
	
}
