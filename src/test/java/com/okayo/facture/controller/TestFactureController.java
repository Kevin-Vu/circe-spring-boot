package com.okayo.facture.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.okayo.facture.dto.designation.CreateDesignationDto;
import com.okayo.facture.dto.facture.FactureDto;
import com.okayo.facture.factory.DesignationFactoryUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
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
public class TestFactureController {

    @Autowired
    private MockMvc mockMvc;

    private static final String API_FACTURE_URL = "/api/facture";
    private JacksonTester<List<FactureDto>> jsonFactureDtoList;
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
    public void testGetAllFactureForClient() throws Exception {

        // Given
        HttpHeaders params = new HttpHeaders();
        params.set("clientId", "1");

        // When
        MockHttpServletResponse response = mockMvc.perform(
                get(API_FACTURE_URL).params(params)
        ).andReturn().getResponse();

        // Then
        List<FactureDto> factureDtoList = jsonFactureDtoList.parse(response.getContentAsString()).getObject();
        Assert.assertFalse(factureDtoList.isEmpty());
    }


    /**
     * Test create facture
     * @throws Exception
     */
    @Test
    public void testCreateFacture() throws Exception {

        // Given
        HttpHeaders params = new HttpHeaders();
        params.add("clientId", "3");
        List<CreateDesignationDto> dtoList = DesignationFactoryUtils.generateCreateDesignationDtoList();

        // When
        MockHttpServletResponse response = mockMvc.perform(
                post(API_FACTURE_URL).accept(MediaType.ALL).content(jsonCreateDesignationDtoList.write(dtoList).getJson())
                        .params(params)
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
    public void testCreateBadFacture() throws Exception {

        // Given
        HttpHeaders params = new HttpHeaders();
        params.add("clientId", "3");
        List<CreateDesignationDto> dtoList = DesignationFactoryUtils.generateCreateDesignationDtoList();
        for(CreateDesignationDto c : dtoList){
            c.setName(null);
        }

        // When
        MockHttpServletResponse response = mockMvc.perform(
                post(API_FACTURE_URL).accept(MediaType.ALL).content(jsonCreateDesignationDtoList.write(dtoList).getJson())
                        .params(params)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Then
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

}
