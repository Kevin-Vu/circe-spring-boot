package com.okayo.facture.service.impl;

import com.okayo.facture.configuration.OkayoConfiguration;
import com.okayo.facture.dto.designation.CreateDesignationDto;
import com.okayo.facture.dto.invoice.InvoiceDto;
import com.okayo.facture.dto.mapper.DesignationMapper;
import com.okayo.facture.dto.mapper.InvoiceMapper;
import com.okayo.facture.entity.ClientEntity;
import com.okayo.facture.entity.DesignationEntity;
import com.okayo.facture.entity.InvoiceEntity;
import com.okayo.facture.repository.InvoiceRepository;
import com.okayo.facture.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private DesignationMapper designationMapper;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private OkayoConfiguration okayoConfiguration;

    @Autowired
    private InvoiceRepository invoiceRepository;


    /**
     * Create facture with several designation for a given client
     * @param createDesignationDtoList : list of CreateDesignationDto
     * @return : FactureEntity
     */
    @Override
    @Transactional
    public InvoiceEntity createFacture(List<CreateDesignationDto> createDesignationDtoList, ClientEntity clientEntity){

        // Convert
        List<DesignationEntity> designationEntityList = designationMapper.convertListCreateDto(createDesignationDtoList);

        float tth = 0;
        float ttc = 0;
        float ttva = 0;
        int qte = 0;

        // Compute tva, ht, ttc
        for(DesignationEntity entity: designationEntityList){
            float unitHt = (entity.getUnitPriceNoTaxes() * entity.getQuantity()) * (1 - entity.getDiscount() / 100);
            float unitTt = unitHt * (1 + entity.getTaxes() / (float) 100);
            ttva += entity.getTaxes() * entity.getQuantity();
            qte += entity.getQuantity();
            ttc += unitTt;
            tth += unitHt;
        }
        if(qte != 0){
            ttva /= qte;
        }

        // Create the facture entity
        InvoiceEntity invoiceEntity = new InvoiceEntity().setClient(clientEntity)
                                                        .setInvoiceDate(Timestamp.valueOf(LocalDateTime.now()))
                                                        .setTotalNoTaxes(tth)
                                                        .setTotalWithTaxes(ttc)
                                                        .setTotalTaxes(ttva)
                                                        .setDesignations(designationEntityList)
                                                        .setExpirityDate(Timestamp.valueOf(LocalDateTime.now().plusMonths(okayoConfiguration.getEcheance())));

        return invoiceRepository.save(invoiceEntity);
    }

    /**
     * Get the facture (invoice) dto for a given client
     * @param clientEntity : ClientEntity
     * @return : list of FactureDto
     */
    @Override
    public List<InvoiceDto> getAllFactureForClient(ClientEntity clientEntity){
        List<InvoiceEntity> invoiceEntityList = invoiceRepository.findAllByClient(clientEntity);
        return invoiceMapper.convertListInvoiceEntity(invoiceEntityList);
    }

}

















