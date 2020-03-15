package com.okayo.facture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okayo.facture.dto.client.ClientDto;
import com.okayo.facture.dto.client.CreateClientDto;
import com.okayo.facture.entity.ClientEntity;
import com.okayo.facture.factory.ClientFactoryUtils;
import com.okayo.facture.service.ClientService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:test.properties"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestClientController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientService clientService;

    private static final String API_CLIENT_URL = "/api/client";
    private JacksonTester<CreateClientDto> jsonCreateClientDto;
    private JacksonTester<ClientDto> jsonClientDto;

    @Before
    public void before() {
        JacksonTester.initFields(this, new ObjectMapper());
    }


    /**
     * Test create client
     * @throws Exception
     */
    @Test
    public void testCreateClient() throws Exception {

        // Given
        CreateClientDto createClientDto = ClientFactoryUtils.generateCreateClientDto();

        // When
        MockHttpServletResponse response = mockMvc.perform(
                post(API_CLIENT_URL).accept(MediaType.ALL).content(jsonCreateClientDto.write(createClientDto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Then
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());

    }


    /**
     * Test get client
     * @throws Exception
     */
    @Test
    public void testGetClient() throws Exception {

        // Given
        String name = "Renault";
        HttpHeaders params = new HttpHeaders();
        params.add("name", name);

        // When
        MockHttpServletResponse response = mockMvc.perform(
                get(API_CLIENT_URL).params(params)
        ).andReturn().getResponse();

        // Then
        ClientDto clientDto = jsonClientDto.parse(response.getContentAsString()).getObject();
        ClientEntity clientEntity = clientService.loadClientByName(name);

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertEquals(clientEntity.getId(), clientDto.getId());
        Assert.assertEquals(clientEntity.getAdresse(), clientDto.getAdresse());
        Assert.assertEquals(clientEntity.getCodeClient(), clientDto.getCodeClient());
        Assert.assertEquals(clientEntity.getCodePostal(), clientDto.getCodePostal());

    }

    /**
     * Test get bad client
     * @throws Exception
     */
    @Test
    public void testGetBadClient() throws Exception {

        // Given
        HttpHeaders params = new HttpHeaders();
        params.add("name", "badCompany");

        // When
        MockHttpServletResponse response = mockMvc.perform(
                get(API_CLIENT_URL).params(params)
        ).andReturn().getResponse();

        // Then
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    /**
     * Test update client
     * @throws Exception
     */
    @Test
    public void testUpdateClient() throws Exception{

        // Given
        ClientDto clientDto = ClientFactoryUtils.generateClientDto().setId(1L);

        // When
        MockHttpServletResponse response = mockMvc.perform(
                patch(API_CLIENT_URL).accept(MediaType.ALL).content(jsonClientDto.write(clientDto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Then
        ClientEntity clientEntity = clientService.loadClientById(1L);

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertEquals(clientEntity.getId(), clientDto.getId());
        Assert.assertEquals(clientEntity.getAdresse(), clientDto.getAdresse());
        Assert.assertNotEquals(clientEntity.getCodeClient(), clientDto.getCodeClient()); // Code client is not updatable
        Assert.assertEquals(clientEntity.getCodePostal(), clientDto.getCodePostal());

    }

    /**
     * Test update bad client
     * @throws Exception
     */
    @Test
    public void testUpdateBadClient() throws Exception{

        // Given
        ClientDto clientDto = ClientFactoryUtils.generateClientDto().setId(-1L);

        // When
        MockHttpServletResponse response = mockMvc.perform(
                patch(API_CLIENT_URL).accept(MediaType.ALL).content(jsonClientDto.write(clientDto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Then
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    /**
     * Test delete client
     * @throws Exception
     */
    @Test
    public void testDeleteClient() throws Exception {

        // Given
        HttpHeaders params = new HttpHeaders();
        params.add("clientId", "2");

        // When
        MockHttpServletResponse response = mockMvc.perform(
                delete(API_CLIENT_URL).params(params)
        ).andReturn().getResponse();

        // Then
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    /**
     * Test delete bad client
     * @throws Exception
     */
    @Test
    public void testDeleteBadClient() throws Exception {

        // Given
        HttpHeaders params = new HttpHeaders();
        params.add("clientId", "-1");

        // When
        MockHttpServletResponse response = mockMvc.perform(
                delete(API_CLIENT_URL).params(params)
        ).andReturn().getResponse();

        // Then
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

}
