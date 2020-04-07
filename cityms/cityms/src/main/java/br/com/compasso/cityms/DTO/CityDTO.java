package br.com.compasso.cityms.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {
	
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Size(min = 2, max = 2)
	private String state;
	
}
