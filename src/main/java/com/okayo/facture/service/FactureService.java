package com.okayo.facture.service;

import com.okayo.facture.dto.designation.CreateDesignationDto;
import com.okayo.facture.dto.facture.FactureDto;
import com.okayo.facture.entity.ClientEntity;
import com.okayo.facture.entity.FactureEntity;

import java.util.List;

public interface FactureService {
    FactureEntity createFacture(List<CreateDesignationDto> createDesignationDtoList, ClientEntity clientEntity);
    List<FactureDto> getAllFactureForClient(ClientEntity clientEntity);
}
