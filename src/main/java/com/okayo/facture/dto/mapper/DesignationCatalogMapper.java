package com.okayo.facture.dto.mapper;

import com.okayo.facture.dto.designation.DesignationCatalogDto;
import com.okayo.facture.entity.DesignationCatalogEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DesignationCatalogMapper {

    DesignationCatalogDto convert(DesignationCatalogEntity designationCatalogEntity);

}
