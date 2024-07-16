package com.lorram.contactmanager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.contactmanager.dto.ClientDTO;
import com.lorram.contactmanager.entities.Client;
import com.lorram.contactmanager.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> list = repository.findAll(pageable);
		return list.map(x -> new ClientDTO(x.getId(), x.getName()));
	}
	
	public Page<ClientDTO> findAll1(Pageable pageable) {
		Page<Client> list = repository.findAll(pageable);
		return list.map(x -> new ClientDTO(x.getId(), x.getName()));
	}
	
	public ClientDTO findById(Long id) {
		Optional<Client> client = repository.findById(id);
		Client entity = client.get();
		return new ClientDTO(entity.getId(), entity.getName());
	}
	
	public ClientDTO update(ClientDTO dto, Long id) {
		Client entity = repository.getReferenceById(id);
		fromDto(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity.getId(), entity.getName());
	}
	
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		try {
			fromDto(dto, entity);
			entity = repository.save(entity);
			} catch(DataIntegrityViolationException e) {
				throw new RuntimeException("Integrity violation");
			}
		return new ClientDTO(entity.getId(), entity.getName());
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	private void fromDto(ClientDTO dto, Client entity ) {
		entity.setName(dto.name());
	}
	
}
