package br.com.compasso.clientms.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.compasso.clientms.dto.CityDTO;

@FeignClient("cityms")
public interface CityClient {

	@RequestMapping(value = "/cityms/v1/cities", params = "name")
	public List<CityDTO> findByName(@RequestParam(required = true) String name);
	
}
