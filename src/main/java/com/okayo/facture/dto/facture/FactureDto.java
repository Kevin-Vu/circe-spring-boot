package com.okayo.facture.dto.facture;

import com.okayo.facture.dto.client.ClientDto;
import com.okayo.facture.dto.designation.DesignationDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class FactureDto {

    private Long id;
    private String dateFacturation;
    private String dateEcheance;
    private float totalTva;
    private float totalHt;
    private float totalTtc;
    private ClientDto client;
    private List<DesignationDto> designations = new ArrayList<>();


}
