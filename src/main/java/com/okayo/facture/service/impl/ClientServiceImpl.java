package com.okayo.facture.service.impl;

import com.okayo.facture.dto.client.ClientDto;
import com.okayo.facture.dto.client.CreateClientDto;
import com.okayo.facture.dto.mapper.ClientMapper;
import com.okayo.facture.entity.ClientEntity;
import com.okayo.facture.exception.notfound.ClientNotFoundException;
import com.okayo.facture.repository.ClientRepository;
import com.okayo.facture.service.ClientService;
import com.okayo.facture.util.ClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    /**
     * Load a client by its name
     * @param name : name
     * @return : ClientEntity
     * @throws ClientNotFoundException
     */
    @Override
    public ClientEntity loadClientByName(String name) throws ClientNotFoundException {
        Optional<ClientEntity> clientEntity = clientRepository.findByName(name);
        if(!clientEntity.isPresent()){
            throw new ClientNotFoundException("Le client n'existe pas : " + name);
        }
        return clientEntity.get();
    }

    /**
     * Load a client by its name
     * @param id : id
     * @return : ClientEntity
     * @throws ClientNotFoundException
     */
    @Override
    public ClientEntity loadClientById(Long id) throws ClientNotFoundException {
        Optional<ClientEntity> clientEntity = clientRepository.findById(id);
        if(!clientEntity.isPresent()){
            throw new ClientNotFoundException("Le client avec l'id n'existe pas : " + id);
        }
        return clientEntity.get();
    }

    /**
     * Create a client
     * @param createClientDto : CreateClientDto
     * @return : ClientEntity
     */
    @Override
    @Transactional
    public void createClient(CreateClientDto createClientDto){
        ClientEntity clientEntity = clientMapper.convert(createClientDto)
                                        .setCodeClient(ClientUtil.createCodeClient());
        clientRepository.save(clientEntity);
    }

    /**
     * Update a client
     * @param clientDto : ClientDto
     * @return : ClientEntity
     */
    @Override
    @Transactional
    public void updateClient(ClientDto clientDto) throws ClientNotFoundException {
        ClientEntity clientEntity = this.loadClientById(clientDto.getId());
        clientEntity.setAdresse(clientDto.getAdresse())
                    .setCodePostal(clientDto.getCodePostal())
                    .setName(clientDto.getName());
        clientRepository.save(clientEntity);
    }

    /**
     * Delete client
     * @param id : id
     */
    @Override
    @Transactional
    public void deleteClientById(Long id) throws ClientNotFoundException {
        ClientEntity clientEntity = loadClientById(id);
        clientRepository.delete(clientEntity);
    }

}
