package com.okayo.facture.factory;

import com.okayo.facture.dto.client.ClientDto;
import com.okayo.facture.dto.client.CreateClientDto;
import com.okayo.facture.enumeration.OkayoAuthorityEnum;
import com.okayo.facture.util.ClientUtil;
import org.apache.commons.lang3.RandomStringUtils;

public class ClientFactoryUtils {

    private ClientFactoryUtils(){}

    public static ClientDto generateClientDto(){
        return new ClientDto().setClientCode(ClientUtil.createCodeClient())
                                .setFirstname(RandomStringUtils.randomAlphanumeric(30))
                                .setLastname(RandomStringUtils.randomAlphanumeric(30))
                                .setEmail(RandomStringUtils.randomAlphabetic(15))
                                .setAuthority(OkayoAuthorityEnum.USER.getValue());
    }

    public static CreateClientDto generateCreateClientDto(){
        return new CreateClientDto()
                .setFirstname(RandomStringUtils.randomAlphanumeric(30))
                .setLastname(RandomStringUtils.randomAlphanumeric(30))
                .setEmail(RandomStringUtils.randomAlphabetic(15))
                .setPassword(RandomStringUtils.randomAlphanumeric(8))
                .setAuthority(OkayoAuthorityEnum.USER.getValue());
    }
}
