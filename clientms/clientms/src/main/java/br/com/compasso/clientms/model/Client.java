package br.com.compasso.clientms.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private GenderEnum gender;
	
	private LocalDateTime birthDate;
	
	private Integer age;
	
	private String city;

}