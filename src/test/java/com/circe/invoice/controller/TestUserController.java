package com.circe.invoice.controller;

import com.circe.invoice.configuration.BaseTest;
import com.circe.invoice.dto.user.CreateUserDto;
import com.circe.invoice.dto.user.UserDto;
import com.circe.invoice.factory.UserFactoryUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:test.properties"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestUserController extends BaseTest {


    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<UserDto> jsonUserDto;
    private JacksonTester<CreateUserDto> jsonCreateUserDto;


    @Before
    public void before() {

        JacksonTester.initFields(this, new ObjectMapper());
        MockitoAnnotations.openMocks(this);
    }


    /**
     * Test get user
     * @throws Exception
     */
    @Test
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsService")
    public void testGetUser() throws Exception {

        // Given
        String url = "/api/auth/user";
        HttpHeaders params = new HttpHeaders();
        params.add("id", "1");

        // When
        MockHttpServletResponse response = mockMvc.perform(
                get(url).params(params)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        UserDto userDto = jsonUserDto.parse(response.getContentAsString()).getObject();

        // Then
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());
        Assert.assertEquals("admin", userDto.getUserCode());

    }

    /**
     * Test delete user
     * @throws Exception
     */
    @Test
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsService")
    @Transactional
    public void testDeleteUser() throws Exception {

        // Given
        String url = "/api/auth/user";
        HttpHeaders params = new HttpHeaders();
        params.add("id", "1");

        // When
        MockHttpServletResponse response = mockMvc.perform(
                delete(url).params(params)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Then
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());

    }

    /**
     * Test create user with "admin" authority
     * @throws Exception
     */
    @Test
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsService")
    @Transactional
    public void testCreateUserAuthority() throws Exception {

        // Given
        CreateUserDto createUserDto = UserFactoryUtils.generateCreateClientDto();
        createUserDto.setAuthority("admin");

        String url = "/api/auth/user";

        // When
        MockHttpServletResponse response = mockMvc.perform(
                post(url).accept(MediaType.ALL).content(jsonCreateUserDto.write(createUserDto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        UserDto userDto = jsonUserDto.parse(response.getContentAsString()).getObject();

        // Then
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());
        Assert.assertEquals(createUserDto.getUserCode(), userDto.getUserCode());

    }


}
