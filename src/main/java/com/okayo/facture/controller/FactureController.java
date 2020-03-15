package com.okayo.facture.controller;

import com.okayo.facture.dto.designation.CreateDesignationDto;
import com.okayo.facture.dto.facture.FactureDto;
import com.okayo.facture.entity.ClientEntity;
import com.okayo.facture.exception.badrequest.DesignationBadRequestException;
import com.okayo.facture.exception.notfound.ClientNotFoundException;
import com.okayo.facture.repository.FactureRepository;
import com.okayo.facture.service.ClientService;
import com.okayo.facture.service.FactureService;
import com.okayo.facture.util.DesignationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FactureController {

    @Autowired
    private FactureService factureService;

    @Autowired
    private ClientService clientService;


    /**
     * Create a facture (invoice) for a client
     * @param createDesignationDtoList : list of CreateDesignationDto
     * @param clientId : id of the Client
     * @return
     */
    @PostMapping(value = "/api/facture")
    public ResponseEntity<HttpStatus> createFacture(@RequestBody List<CreateDesignationDto> createDesignationDtoList, @RequestParam Long clientId) throws ClientNotFoundException, DesignationBadRequestException {

        if(clientId == null){
            throw new ClientNotFoundException("Le client est null");
        }
        if(!DesignationUtil.checkListCreateDesignationInput(createDesignationDtoList)){
            throw new DesignationBadRequestException("Les designations contiennent des mauvaises informations");
        }

        ClientEntity clientEntity = clientService.loadClientById(clientId);
        factureService.createFacture(createDesignationDtoList, clientEntity);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Get all facture for a given client
     * @param clientId : client id
     * @return : list of FactureDto
     * @throws ClientNotFoundException
     */
    @GetMapping(value = "/api/facture")
    public ResponseEntity<List<FactureDto>> getAllFactureForClient(@RequestParam Long clientId) throws ClientNotFoundException {
        if(clientId == null){
            throw new ClientNotFoundException("Le client est null");
        }
        ClientEntity clientEntity = clientService.loadClientById(clientId);
        List<FactureDto> factureDtoList = factureService.getAllFactureForClient(clientEntity);

        return new ResponseEntity<>(factureDtoList, HttpStatus.OK);
    }


}
