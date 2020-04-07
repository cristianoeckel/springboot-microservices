package br.com.compasso.clientms.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.compasso.clientms.client.CityClient;
import br.com.compasso.clientms.dto.ClientDTO;
import br.com.compasso.clientms.dto.UpdateClientNameDTO;
import br.com.compasso.clientms.exception.CityNotFoundException;
import br.com.compasso.clientms.model.Client;
import br.com.compasso.clientms.repository.ClientRepository;
import feign.FeignException;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	private CityClient cityClient;

	@Autowired
	ModelMapper modelMapper;

	public ClientDTO save(@RequestBody(required = true) ClientDTO clientDTO) {
		try {
			cityClient.findByName(clientDTO.getCity());
		} catch (FeignException e) {
			if (e.status() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				throw new CityNotFoundException();
			}
			throw e;
		}
		Client client = modelMapper.map(clientDTO, Client.class);
		clientRepository.save(client);
		clientDTO.setId(client.getId());
		return clientDTO;
	}

	public ClientDTO findById(Long id) {
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException("No client found with this ID.", 1));
		ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
		return clientDTO;
	}

	public List<ClientDTO> findClientByName(String name) {
		List<Client> clients = findClientName(name);
		List<ClientDTO> clientsDTO = Arrays.asList(modelMapper.map(clients, ClientDTO[].class));
		return clientsDTO;
	}

	public UpdateClientNameDTO updateClientName(Long id, UpdateClientNameDTO updateClientNameDTO) {
		Client client = findClientById(id);
		client.setName(updateClientNameDTO.getName());
		clientRepository.save(client);
		return updateClientNameDTO;
	}

	public void deleteClient(Long id) {
		if(clientRepository.existsById(id))
		clientRepository.deleteById(id);
	}

	private Client findClientById(Long id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException("No client found with this NAME", 1));
	}

	private List<Client> findClientName(String name) {
		return clientRepository.findByNameIgnoreCase(name)
				.orElseThrow(() -> new EmptyResultDataAccessException("No client found with this NAME", 1));
	}

}
