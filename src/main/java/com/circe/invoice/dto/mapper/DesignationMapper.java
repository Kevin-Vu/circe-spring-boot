package com.circe.invoice.dto.mapper;

import com.circe.invoice.dto.designation.DesignationCatalogDto;
import com.circe.invoice.dto.designation.DesignationDto;
import com.circe.invoice.entity.data.DesignationEntity;
import com.circe.invoice.entity.referential.DesignationCatalogEntity;
import java.util.List;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DesignationMapper {

  @Mapping(target = "productType", source = "entity.productType.productType")
  @Mapping(target = "amount", source = "entity.productType.amount")
  DesignationCatalogDto convert(DesignationCatalogEntity entity);

  @Named("convertDesignationEntityToDto")
  DesignationDto convert(DesignationEntity entity);

  @IterableMapping(
      qualifiedByName = "convertDesignationEntityToDto",
      nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
  List<DesignationDto> convert(List<DesignationEntity> entityList);
}
