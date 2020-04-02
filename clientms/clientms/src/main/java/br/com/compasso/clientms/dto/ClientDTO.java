package br.com.compasso.clientms.dto;

import java.time.LocalDate;

import br.com.compasso.clientms.model.Client;
import br.com.compasso.clientms.repository.ClientRepository;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
