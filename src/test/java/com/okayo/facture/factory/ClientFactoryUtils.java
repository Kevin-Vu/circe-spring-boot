package com.okayo.facture.factory;

import com.okayo.facture.dto.client.ClientDto;
import com.okayo.facture.dto.client.CreateClientDto;
import com.okayo.facture.util.ClientUtil;
import org.apache.commons.lang3.RandomStringUtils;

public class ClientFactoryUtils {

    private ClientFactoryUtils(){}

    public static ClientDto generateClientDto(){
        return new ClientDto().setAdresse(RandomStringUtils.randomAlphanumeric(30))
                                .setCodeClient(ClientUtil.createCodeClient())
                                .setCodePostal(Integer.valueOf(RandomStringUtils.randomNumeric(5)))
                                .setName(RandomStringUtils.randomAlphanumeric(30));
    }

    public static CreateClientDto generateCreateClientDto(){
        return new CreateClientDto().setAdresse(RandomStringUtils.randomAlphanumeric(30))
                .setCodePostal(Integer.valueOf(RandomStringUtils.randomNumeric(5)))
                .setName(RandomStringUtils.randomAlphanumeric(30));
    }
}
