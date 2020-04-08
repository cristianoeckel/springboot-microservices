package br.com.compasso.clientms.dto;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.constraints.NotBlank;

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
	@NotBlank
	private String name;
	@NotBlank
	private String gender;

	private LocalDate birthDate;
	
	private Integer age;
	@NotBlank
	private String city;
	
	public static Integer age(final LocalDate birthDate) {
		return (Period.between(birthDate, LocalDate.now()).getYears());
		
	}

}
