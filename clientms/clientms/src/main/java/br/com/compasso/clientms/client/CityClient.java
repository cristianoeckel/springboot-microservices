package br.com.compasso.clientms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.compasso.clientms.dto.CityDTO;

@FeignClient("city")
public interface CityClient {

	@GetMapping(value = "cities", params = "name")
	public CityDTO findByName(@RequestParam(required = true) String name);

}
