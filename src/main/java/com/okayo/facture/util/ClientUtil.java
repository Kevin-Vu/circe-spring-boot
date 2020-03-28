package com.okayo.facture.util;

import com.okayo.facture.dto.client.ClientDto;
import com.okayo.facture.dto.client.CreateClientDto;
import com.okayo.facture.entity.ClientEntity;
import com.okayo.facture.security.CurrentUser;
import org.apache.commons.lang3.StringUtils;

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
        return !StringUtils.isBlank(createClientDto.getFirstname()) && !StringUtils.isBlank(createClientDto.getLastname()) &&
                !StringUtils.isBlank(createClientDto.getPassword()) && !StringUtils.isBlank(createClientDto.getEmail()) &&
                !StringUtils.isBlank(createClientDto.getCompany()) && !StringUtils.isBlank(createClientDto.getAuthority());
    }

    /**
     * Check the content of a ClientDto
     * @param clientDto : ClientDto
     * @return : true if input is valid
     */
    public static boolean checkClientInput(ClientDto clientDto){
        if(clientDto == null){
            return false;
        }
        if(clientDto.getId() == null || clientDto.getId() < 0){
            return false;
        }
        return !StringUtils.isBlank(clientDto.getFirstname()) && !StringUtils.isBlank(clientDto.getLastname()) &&
                !StringUtils.isBlank(clientDto.getEmail()) && !StringUtils.isBlank(clientDto.getCompany()) &&
                !StringUtils.isBlank(clientDto.getAuthority());
    }

    /**
     * Check if the user belongs to the company
     * @param user : CurrentUser
     * @param company : company name
     * @return : boolean
     */
    public static boolean checkUserBelongsToCompany(CurrentUser user, String company){
        return StringUtils.compare(user.getCompanyName(), company) == 0;
    }

    /**
     * Check if the user belongs to the company
     * @param user : CurrentUser
     * @param clientEntity : ClientEntity
     * @return : boolean
     */
    public static boolean checkUserBelongsToCompany(CurrentUser user, ClientEntity clientEntity){
        return StringUtils.compare(user.getCompanyName(), clientEntity.getCompanyEntity().getName()) == 0;
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
