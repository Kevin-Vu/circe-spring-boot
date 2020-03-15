package com.okayo.facture.dto.mapper;

import com.okayo.facture.dto.client.ClientDto;
import com.okayo.facture.dto.client.CreateClientDto;
import com.okayo.facture.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto convert(ClientEntity clientEntity);
    ClientEntity convert(ClientDto clientDto);
    ClientEntity convert(CreateClientDto createClientDto);

}
