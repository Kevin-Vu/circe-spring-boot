package com.okayo.facture.controller;

import com.okayo.facture.dto.client.ClientDto;
import com.okayo.facture.dto.client.CreateClientDto;
import com.okayo.facture.dto.mapper.ClientMapper;
import com.okayo.facture.entity.ClientEntity;
import com.okayo.facture.exception.badrequest.ClientBadRequestException;
import com.okayo.facture.exception.notfound.ClientNotFoundException;
import com.okayo.facture.security.CurrentUser;
import com.okayo.facture.service.ClientService;
import com.okayo.facture.util.ClientUtil;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Créer un nouveau client")
    @PostMapping(value = "/api/auth/manager/client")
    public ResponseEntity<HttpStatus> createClient(CurrentUser user, @RequestBody CreateClientDto createClientDto) throws ClientBadRequestException {
        if(!ClientUtil.checkCreateClientInput(createClientDto) || !ClientUtil.checkUserBelongsToCompany(user, createClientDto.getCompany())){
            throw new ClientBadRequestException("Le client contient des informations erronés");
        }
        if(!ClientUtil.checkUserBelongsToCompany(user, createClientDto.getCompany())){
            throw new ClientBadRequestException("Impossible de créer un client d'une autre entreprise");
        }
        clientService.createClient(createClientDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Read a client in database from a given name
     * @param clientCode : client code
     * @return : ClientDto
     * @throws ClientNotFoundException
     */
    @ApiOperation(value = "Récupère un client par son code client")
    @GetMapping(value = "/api/auth/client")
    public ResponseEntity<ClientDto> getClientByCodeClient(CurrentUser user, @RequestParam String clientCode) throws ClientNotFoundException, ClientBadRequestException {
        ClientEntity clientEntity = clientService.loadClientByCodeClient(clientCode);
        if(!ClientUtil.checkUserBelongsToCompany(user, clientEntity)){
            throw new ClientBadRequestException("Impossible de récupérer un client d'une autre entreprise");
        }
        ClientDto clientDto = clientMapper.convert(clientEntity);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    /**
     * Get current client
     * @param user : CurrentUser
     * @return : ClientDto
     * @throws ClientNotFoundException
     * @throws ClientBadRequestException
     */
    @ApiOperation(value = "Récupère le client courant")
    @GetMapping(value = "/api/auth/client/current")
    public ResponseEntity<ClientDto> getCurrentClient(CurrentUser user) throws ClientNotFoundException {
        ClientEntity clientEntity = clientService.loadClientById(user.getId());
        ClientDto clientDto = clientMapper.convert(clientEntity);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }


    /**
     * Update a client
     * @param clientDto : ClientDto
     * @return : Response status
     * @throws ClientNotFoundException
     */
    @ApiOperation(value = "Met à jour un client")
    @PatchMapping(value = "/api/auth/manager/client")
    public ResponseEntity<HttpStatus> updateClient(CurrentUser user, @RequestBody ClientDto clientDto) throws ClientNotFoundException, ClientBadRequestException {
        if(!ClientUtil.checkClientInput(clientDto)){
            throw new ClientBadRequestException("Le client contient des informations erronés");
        }
        if(!ClientUtil.checkUserBelongsToCompany(user, clientDto.getCompany())){
            throw new ClientBadRequestException("Impossible de mettre à jour un client d'une autre entreprise");
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
    @ApiOperation(value = "Supprime un client d'après son id")
    @DeleteMapping(value = "/api/auth/admin/client")
    public ResponseEntity<HttpStatus> deleteClientById(CurrentUser user, @RequestParam Long clientId) throws ClientNotFoundException {
        if(clientId == null){
            throw new ClientNotFoundException("Le client est null");
        }
        clientService.deleteClientById(clientId, user.getCompanyName());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
