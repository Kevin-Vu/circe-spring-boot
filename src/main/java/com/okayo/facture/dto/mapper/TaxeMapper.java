package com.okayo.facture.dto.mapper;

import com.okayo.facture.dto.taxe.TaxeDto;
import com.okayo.facture.entity.TaxeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaxeMapper {

    @Mapping(source = "productTypeEntity.name", target = "productType")
    TaxeDto convert(TaxeEntity taxeEntity);
}
