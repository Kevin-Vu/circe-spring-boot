package com.okayo.facture.dto.taxe;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class TaxeDto {

    private Float amount;
    private Timestamp dateFacturation;
    private String productType;
}
