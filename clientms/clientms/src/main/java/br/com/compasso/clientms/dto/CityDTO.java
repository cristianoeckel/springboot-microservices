package br.com.compasso.clientms.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class CityDTO {
	@NotBlank
	private String name;
	
	@NotBlank
	@Size(min = 2, max = 2)
	private String state;
	
}

