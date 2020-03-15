package com.okayo.facture.dto.mapper;

import com.okayo.facture.dto.designation.DesignationCatalogueDto;
import com.okayo.facture.entity.DesignationCatalogueEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DesignationCatalogueMapper {

    DesignationCatalogueDto convert(DesignationCatalogueEntity designationCatalogueEntity);
    DesignationCatalogueEntity convert(DesignationCatalogueDto designationCatalogueDto);

}
