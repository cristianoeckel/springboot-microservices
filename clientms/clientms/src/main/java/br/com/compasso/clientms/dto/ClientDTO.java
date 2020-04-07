package br.com.compasso.clientms.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

	private Long id;
	
	private String name;
	
	private String gender;
	
	private LocalDate birthDate;
	
	private Integer age;
	
	private String city;
	
}
