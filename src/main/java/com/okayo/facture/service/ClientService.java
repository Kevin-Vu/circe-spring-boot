package com.okayo.facture.service;

import com.okayo.facture.dto.client.ClientDto;
import com.okayo.facture.dto.client.CreateClientDto;
import com.okayo.facture.entity.ClientEntity;
import com.okayo.facture.exception.notfound.ClientNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ClientService extends UserDetailsService {

    ClientEntity loadClientByCodeClient(String codeClient) throws ClientNotFoundException;
    ClientEntity loadClientById(Long id) throws ClientNotFoundException;
    void createClient(CreateClientDto clientDto);
    void updateClient(ClientDto clientDto) throws ClientNotFoundException;
    void deleteClientById(Long id, String company) throws ClientNotFoundException;
}
