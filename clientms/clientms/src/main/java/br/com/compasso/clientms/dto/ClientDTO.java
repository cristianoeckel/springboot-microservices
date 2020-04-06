package br.com.compasso.clientms.dto;

import java.time.LocalDate;

import br.com.compasso.clientms.model.Client;
import br.com.compasso.clientms.repository.ClientRepository;
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

	public ClientDTO(Client client) {
		this.id = client.getId();
		this.name = client.getName();
		this.gender = client.getGender();
		this.birthDate = client.getBirthDate();
		this.age = client.getAge();
		this.city = client.getCity();
	}
	
	public Client updateClient(Long id, ClientRepository clientRepository) {
		Client client = clientRepository.getOne(id);
		client.setName(this.name);
		client.setGender(this.gender);
		client.setAge(this.age);
		client.setBirthDate(this.birthDate);
		client.setCity(this.city);
		
		return client;
	}
	
}
