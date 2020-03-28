package com.okayo.facture.dto.mapper;

import com.okayo.facture.dto.designation.CreateDesignationDto;
import com.okayo.facture.entity.DesignationEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = DesignationCatalogMapper.class)
public interface DesignationMapper {

    @Named("convertCreateDesignationDtoToEntity")
    DesignationEntity convert(CreateDesignationDto createDesignationDto);

    @IterableMapping(qualifiedByName = "convertCreateDesignationDtoToEntity", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<DesignationEntity> convertListCreateDto(List<CreateDesignationDto> createDesignationDtoList);

}
