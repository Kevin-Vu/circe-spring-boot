package com.circe.invoice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.circe.invoice.configuration.TestContainers;
import com.circe.invoice.dto.user.CreateUserDto;
import com.circe.invoice.dto.user.UserDto;
import com.circe.invoice.factory.UserFactoryUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

class TestUserController extends TestContainers {

  @Autowired
  private MockMvc mockMvc;


  private JacksonTester<UserDto> jsonUserDto;
  private JacksonTester<CreateUserDto> jsonCreateUserDto;

  @BeforeEach
  public void before() {

    JacksonTester.initFields(this, new ObjectMapper());
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Test get user
   *
   * @throws Exception
   */
  @Test
  @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsService")
  void testGetUser() throws Exception {

    // Given
    String url = "/api/auth/user";
    HttpHeaders params = new HttpHeaders();
    params.add("id", "1");

    // When
    MockHttpServletResponse response =
        mockMvc
            .perform(get(url).params(params).contentType(MediaType.APPLICATION_JSON))
            .andReturn()
            .getResponse();

    UserDto userDto = jsonUserDto.parse(response.getContentAsString()).getObject();

    // Then
    Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    Assertions.assertEquals("admin", userDto.getUserCode());
  }

  /**
   * Test delete user
   *
   * @throws Exception
   */
  @Test
  @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsService")
  @Transactional
  void testDeleteUser() throws Exception {

    // Given
    String url = "/api/auth/user";
    HttpHeaders params = new HttpHeaders();
    params.add("id", "1");

    // When
    MockHttpServletResponse response =
        mockMvc
            .perform(delete(url).params(params).contentType(MediaType.APPLICATION_JSON))
            .andReturn()
            .getResponse();

    // Then
    Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  /**
   * Test create user with "admin" authority
   *
   * @throws Exception
   */
  @Test
  @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsService")
  @Transactional
  void testCreateUserAuthority() throws Exception {

    // Given
    CreateUserDto createUserDto = UserFactoryUtils.generateCreateClientDto();
    createUserDto.setAuthority("admin");

    String url = "/api/auth/user";

    // When
    MockHttpServletResponse response =
        mockMvc
            .perform(
                post(url)
                    .accept(MediaType.ALL)
                    .content(jsonCreateUserDto.write(createUserDto).getJson())
                    .contentType(MediaType.APPLICATION_JSON))
            .andReturn()
            .getResponse();

    UserDto userDto = jsonUserDto.parse(response.getContentAsString()).getObject();

    // Then
    Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    Assertions.assertEquals(createUserDto.getUserCode(), userDto.getUserCode());
  }
}
