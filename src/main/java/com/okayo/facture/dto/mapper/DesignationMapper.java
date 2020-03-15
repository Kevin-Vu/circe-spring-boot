package com.okayo.facture.dto.mapper;

import com.okayo.facture.dto.designation.CreateDesignationDto;
import com.okayo.facture.dto.designation.DesignationDto;
import com.okayo.facture.entity.DesignationEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = DesignationCatalogueMapper.class)
public interface DesignationMapper {

    @Named("convertDesignationEntityToDto")
    DesignationDto convert(DesignationEntity designationEntity);

    @Named("convertDesignationDtoToEntity")
    DesignationEntity convert(DesignationDto designationDto);

    @Named("convertCreateDesignationDtoToEntity")
    DesignationEntity convert(CreateDesignationDto createDesignationDto);


    @IterableMapping(qualifiedByName = "convertDesignationEntityToDto", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<DesignationDto> convertList(List<DesignationEntity> designationEntityList);

    @IterableMapping(qualifiedByName = "convertDesignationDtoToEntity", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<DesignationEntity> convertListDto(List<DesignationDto> designationDtoList);

    @IterableMapping(qualifiedByName = "convertCreateDesignationDtoToEntity", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<DesignationEntity> convertListCreateDto(List<CreateDesignationDto> createDesignationDtoList);

}
