package com.okayo.facture.dto.mapper;

import com.okayo.facture.dto.facture.FactureDto;
import com.okayo.facture.entity.FactureEntity;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = "spring", uses = DesignationMapper.class)
public interface FactureMapper {

    @Mapping(target = "dateEcheance", source = "dateEcheance", qualifiedByName = "timestampToString")
    @Mapping(target = "dateFacturation" , source = "dateFacturation", qualifiedByName = "timestampToString")
    @Named(value = "convertFactureEntityToDto")
    FactureDto convert(FactureEntity factureEntity);

    @Named("timestampToString")
    default String timestampConvert(Timestamp timestamp){
        return timestamp.toString();
    }

    @Mapping(target = "dateEcheance", source = "dateEcheance", qualifiedByName = "stringToTimestamp")
    @Mapping(target = "dateFacturation" , source = "dateFacturation", qualifiedByName = "stringToTimestamp")
    FactureEntity convert(FactureDto factureDto);

    @Named("stringToTimestamp")
    default Timestamp timestampConvertString(String stringDate){
        return Timestamp.valueOf(stringDate);
    }

    @IterableMapping(qualifiedByName = "convertFactureEntityToDto", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<FactureDto> convertListFactureEntity(List<FactureEntity> factureEntityList);

}
