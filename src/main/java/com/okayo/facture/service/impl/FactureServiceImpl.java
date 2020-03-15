package com.okayo.facture.service.impl;

import com.okayo.facture.configuration.OkayoConfiguration;
import com.okayo.facture.dto.designation.CreateDesignationDto;
import com.okayo.facture.dto.facture.FactureDto;
import com.okayo.facture.dto.mapper.DesignationMapper;
import com.okayo.facture.dto.mapper.FactureMapper;
import com.okayo.facture.entity.ClientEntity;
import com.okayo.facture.entity.DesignationEntity;
import com.okayo.facture.entity.FactureEntity;
import com.okayo.facture.repository.FactureRepository;
import com.okayo.facture.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FactureServiceImpl implements FactureService {

    @Autowired
    private DesignationMapper designationMapper;

    @Autowired
    private FactureMapper factureMapper;

    @Autowired
    private OkayoConfiguration okayoConfiguration;

    @Autowired
    private FactureRepository factureRepository;


    /**
     * Create facture with several designation for a given client
     * @param createDesignationDtoList : list of CreateDesignationDto
     * @return : FactureEntity
     */
    @Override
    @Transactional
    public FactureEntity createFacture(List<CreateDesignationDto> createDesignationDtoList, ClientEntity clientEntity){

        // Convert
        List<DesignationEntity> designationEntityList = designationMapper.convertListCreateDto(createDesignationDtoList);

        float tth = 0;
        float ttc = 0;
        float ttva = 0;
        int qte = 0;

        // Compute tva, ht, ttc
        for(DesignationEntity entity: designationEntityList){
            float unitHt = (entity.getPrixUnitHt() * entity.getQuantite()) * (1 - entity.getReduction() / 100);
            float unitTt = unitHt * (1 + entity.getTva() / (float) 100);
            ttva += entity.getTva() * entity.getQuantite();
            qte += entity.getQuantite();
            ttc += unitTt;
            tth += unitHt;
        }
        if(qte != 0){
            ttva /= qte;
        }

        // Create the facture entity
        FactureEntity factureEntity = new FactureEntity().setClient(clientEntity)
                                                        .setDateFacturation(Timestamp.valueOf(LocalDateTime.now()))
                                                        .setTotalHt(tth)
                                                        .setTotalTtc(ttc)
                                                        .setTotalTva(ttva)
                                                        .setDesignations(designationEntityList)
                                                        .setDateEcheance(Timestamp.valueOf(LocalDateTime.now().plusMonths(okayoConfiguration.getEcheance())));

        return factureRepository.save(factureEntity);
    }

    /**
     * Get the facture (invoice) dto for a given client
     * @param clientEntity : ClientEntity
     * @return : list of FactureDto
     */
    @Override
    public List<FactureDto> getAllFactureForClient(ClientEntity clientEntity){
        List<FactureEntity> factureEntityList = factureRepository.findAllByClient(clientEntity);
        return factureMapper.convertListFactureEntity(factureEntityList);
    }

}

















