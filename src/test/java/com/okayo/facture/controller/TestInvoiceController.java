package com.okayo.facture.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.okayo.facture.configuration.BaseTest;
import com.okayo.facture.dto.designation.CreateDesignationDto;
import com.okayo.facture.dto.invoice.InvoiceDto;
import com.okayo.facture.factory.DesignationFactoryUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:test.properties"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestInvoiceController extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<List<InvoiceDto>> jsonFactureDtoList;
    private JacksonTester<List<CreateDesignationDto>> jsonCreateDesignationDtoList;


    @Before
    public void before() {
        JacksonTester.initFields(this, new ObjectMapper());
    }


    /**
     * Test get all facture for client
     * @throws Exception
     */
    @Test
    @WithUserDetails(value = "CU-AD-SG-585", userDetailsServiceBeanName = "userDetailsService")
    public void testGetAllFactureForClient() throws Exception {

        // When
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/auth/facture")
        ).andReturn().getResponse();

        // Then
        List<InvoiceDto> invoiceDtoList = jsonFactureDtoList.parse(response.getContentAsString()).getObject();
        Assert.assertFalse(invoiceDtoList.isEmpty());
    }


    /**
     * Test create facture
     * @throws Exception
     */
    @Test
    @WithUserDetails(value = "CU-AD-SG-585", userDetailsServiceBeanName = "userDetailsService")
    public void testCreateFacture() throws Exception {

        // Given
        List<CreateDesignationDto> dtoList = DesignationFactoryUtils.generateCreateDesignationDtoList();

        // When
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/auth/manager/facture").accept(MediaType.ALL).content(jsonCreateDesignationDtoList.write(dtoList).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Then
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    /**
     * Test create bad facture
     * @throws Exception
     */
    @Test
    @WithUserDetails(value = "CU-AD-SG-585", userDetailsServiceBeanName = "userDetailsService")
    public void testCreateBadFacture() throws Exception {

        // Given
        List<CreateDesignationDto> dtoList = DesignationFactoryUtils.generateCreateDesignationDtoList();
        for(CreateDesignationDto c : dtoList){
            c.setName(null);
        }

        // When
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/auth/manager/facture").accept(MediaType.ALL).content(jsonCreateDesignationDtoList.write(dtoList).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Then
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

}
