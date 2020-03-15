package com.okayo.facture.controller;

import com.okayo.facture.dto.client.ClientDto;
import com.okayo.facture.dto.client.CreateClientDto;
import com.okayo.facture.dto.mapper.ClientMapper;
import com.okayo.facture.entity.ClientEntity;
import com.okayo.facture.exception.badrequest.ClientBadRequestException;
import com.okayo.facture.exception.notfound.ClientNotFoundException;
import com.okayo.facture.service.ClientService;
import com.okayo.facture.util.ClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    /**
     * Create a ClientEntity in database from a CreateClientDto
     * @param createClientDto : CreateClientDto
     * @return : Response status
     */
    @PostMapping(value = "/api/client")
    public ResponseEntity<HttpStatus> createClient(@RequestBody CreateClientDto createClientDto) throws ClientBadRequestException {
        if(!ClientUtil.checkCreateClientInput(createClientDto)){
            throw new ClientBadRequestException("Le client contient des informations erronés");
        }

        clientService.createClient(createClientDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Read a client in database from a given name
     * @param name : name
     * @return : ClientDto
     * @throws ClientNotFoundException
     */
    @GetMapping(value = "/api/client")
    public ResponseEntity<ClientDto> getClientByName(@RequestParam String name) throws ClientNotFoundException {
        ClientEntity clientEntity = clientService.loadClientByName(name);
        ClientDto clientDto = clientMapper.convert(clientEntity);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    /**
     * Update a client
     * @param clientDto : ClientDto
     * @return : Response status
     * @throws ClientNotFoundException
     */
    @PatchMapping(value = "/api/client")
    public ResponseEntity<HttpStatus> updateClient(@RequestBody ClientDto clientDto) throws ClientNotFoundException, ClientBadRequestException {
        if(!ClientUtil.checkClientInput(clientDto)){
            throw new ClientBadRequestException("Le client contient des informations erronés");
        }
        clientService.updateClient(clientDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Delete a client for a given id
     * @param clientId : id of the client to delete
     * @return : Response status
     * @throws ClientNotFoundException
     */
    @DeleteMapping(value = "/api/client")
    public ResponseEntity<HttpStatus> deleteClientById(@RequestParam Long clientId) throws ClientNotFoundException {
        if(clientId == null){
            throw new ClientNotFoundException("Le client est null");
        }
        clientService.deleteClientById(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
