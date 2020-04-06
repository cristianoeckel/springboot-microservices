package br.com.compasso.clientms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.compasso.clientms.dto.CityDTO;

@FeignClient("cityms")
public interface CityClient {

	@RequestMapping(value = "cities", params = "name")
	public CityDTO findByName(@RequestParam(required = true) String name);
	
}
