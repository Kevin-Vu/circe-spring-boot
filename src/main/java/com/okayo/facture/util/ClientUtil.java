package com.okayo.facture.util;

import com.okayo.facture.dto.client.ClientDto;
import com.okayo.facture.dto.client.CreateClientDto;

import java.util.UUID;

public class ClientUtil {

    private ClientUtil(){}

    /**
     * Check the content of a CreateClientDto
     * @param createClientDto : CreateClientDto
     * @return : true if input is valid
     */
    public static boolean checkCreateClientInput(CreateClientDto createClientDto){
        if(createClientDto == null){
            return false;
        }
        if(createClientDto.getAdresse() == null || createClientDto.getAdresse().isEmpty()){
            return false;
        }
        if(createClientDto.getCodePostal() == null){
            return false;
        }
        if(createClientDto.getName() == null || createClientDto.getName().isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * Check the content of a CreateClientDto
     * @param clientDto : CreateClientDto
     * @return : true if input is valid
     */
    public static boolean checkClientInput(ClientDto clientDto){
        if(clientDto == null){
            return false;
        }
        if(clientDto.getId() == null){
            return false;
        }
        if(clientDto.getAdresse() == null || clientDto.getAdresse().isEmpty()){
            return false;
        }
        if(clientDto.getCodePostal() == null){
            return false;
        }
        if(clientDto.getName() == null || clientDto.getName().isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * Create a code client
     * (Actually a uuid is unique, there a few chance that we generate two same uuid
     * but it might be interesting to verify that when save in database)
     * @return : uuid
     */
    public static String createCodeClient(){
        return UUID.randomUUID().toString();
    }

}
