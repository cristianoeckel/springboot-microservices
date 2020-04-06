package br.com.compasso.clientms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.compasso.clientms.client.CityClient;
import br.com.compasso.clientms.dto.ClientDTO;
import br.com.compasso.clientms.exception.CityNotFoundException;
import br.com.compasso.clientms.exception.ClientAlreadyExistsException;
import br.com.compasso.clientms.model.Client;
import br.com.compasso.clientms.repository.ClientRepository;
import feign.FeignException;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	private CityClient cityClient;

	public Client save(@RequestBody(required = true) ClientDTO clientDTO){
		Optional<Client> clientOpt = clientRepository.findByNameIgnoreCase(clientDTO.getName());
		if (clientOpt.isPresent()) {
			throw new ClientAlreadyExistsException();
		}
		try {
			cityClient.findByName(clientDTO.getCity());
		} catch (FeignException e) {
			if (e.status() == HttpStatus.NOT_FOUND.value()) {
				throw new CityNotFoundException();
			}
			throw e;
		}
		Client client = Client.builder().name(clientDTO.getName()).gender(clientDTO.getGender())
				.birthDate(clientDTO.getBirthDate()).age(clientDTO.getAge()).city(clientDTO.getCity()).build();

		return clientRepository.save(client);
	}

	public Client findById(Long id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException("No customers found with this id.", 1));
	}

	public Client findClientByName(String name) {
		return clientRepository.findByNameIgnoreCase(name)
				.orElseThrow(() -> new EmptyResultDataAccessException("No client found with this NAME", 1));
	}

	public void updateClient(Long id, Client client) {
		Optional<Client> optional = clientRepository.findById(id);
		if (optional.isPresent()) {
			ClientDTO newClient = new ClientDTO(client);
			client = newClient.updateClient(id, clientRepository);
			clientRepository.save(client);
		}
	}

	public void deleteClient(Long id) {
		Optional<Client> client = clientRepository.findById(id);
		if (client.isPresent()) {
			clientRepository.deleteById(id);
		}
	}
	
}
