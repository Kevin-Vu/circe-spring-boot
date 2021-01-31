package com.circe.invoice.service.impl;

import com.circe.invoice.configuration.CirceConfiguration;
import com.circe.invoice.dto.invoice.InvoiceDto;
import com.circe.invoice.dto.mapper.DesignationMapper;
import com.circe.invoice.dto.mapper.InvoiceMapper;
import com.circe.invoice.repository.data.InvoiceRepository;
import com.circe.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private DesignationMapper designationMapper;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private CirceConfiguration circeConfiguration;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceDto> getAllInvoicesForCustomer(Integer id){
        return invoiceMapper.convertListInvoiceEntity(invoiceRepository.findAllByCustomerId(id));
    }

}

















