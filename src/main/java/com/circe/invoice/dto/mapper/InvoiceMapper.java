package com.circe.invoice.dto.mapper;

import com.circe.invoice.entity.data.InvoiceEntity;
import com.circe.invoice.dto.invoice.InvoiceDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = DesignationMapper.class)
public interface InvoiceMapper {

    @Named(value = "convertInvoiceEntityToDto")
    InvoiceDto convert(InvoiceEntity invoiceEntity);

    @IterableMapping(qualifiedByName = "convertInvoiceEntityToDto", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<InvoiceDto> convertListInvoiceEntity(List<InvoiceEntity> invoiceEntityList);

}
