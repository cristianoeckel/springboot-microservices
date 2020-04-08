package br.com.compasso.clientms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClientNameDTO {
	
	private Long id;

	private String name;

}
