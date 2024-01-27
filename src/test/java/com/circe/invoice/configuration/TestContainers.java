package com.circe.invoice.configuration;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@TestPropertySource(locations = {"classpath:test.properties"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class TestContainers {

  @LocalServerPort
  private Integer port;

  private static final String DB_NAME_DATA = "CIRCE_DATA";
  private static final String DB_NAME_REFERENTIAL = "CIRCE_REFERENTIAL";
  private static final String DB_USER = "circe";
  private static final String DB_PASS = "circe";

  @Container
  public static PostgreSQLContainer<?> postgreSQLContainerData =
        (PostgreSQLContainer<?>)
            new PostgreSQLContainer("postgres:11.1")
                .withInitScript("dump_circe_data.sql")
                .withDatabaseName(DB_NAME_DATA)
                .withUsername(DB_USER)
                .withPassword(DB_PASS);

  @Container
  public static PostgreSQLContainer<?> postgreSQLContainerReferential =
      (PostgreSQLContainer<?>)
          new PostgreSQLContainer("postgres:11.1")
              .withInitScript("dump_circe_referential.sql")
              .withDatabaseName(DB_NAME_REFERENTIAL)
              .withUsername(DB_USER)
              .withPassword(DB_PASS);

  @BeforeAll
  public static void setUp() {
    postgreSQLContainerData.withInitScript("dump_circe_data.sql");
    postgreSQLContainerData.start();

    postgreSQLContainerReferential.withInitScript("dump_circe_referential.sql");
    postgreSQLContainerReferential.start();
  }

  @BeforeEach
  void setUpPort() {
    RestAssured.baseURI = "http://localhost:" + port;
  }
  
  @AfterAll
  public static void tearDown() {
    postgreSQLContainerData.stop();
    postgreSQLContainerReferential.stop();
  }

  @DynamicPropertySource
  public static void overrideProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.data.jdbc-url", postgreSQLContainerData::getJdbcUrl);
    registry.add("spring.datasource.data.username", postgreSQLContainerData::getUsername);
    registry.add("spring.datasource.data.password", postgreSQLContainerData::getPassword);

    registry.add("spring.datasource.referential.jdbc-url", postgreSQLContainerReferential::getJdbcUrl);
    registry.add("spring.datasource.referential.username", postgreSQLContainerReferential::getUsername);
    registry.add("spring.datasource.referential.password", postgreSQLContainerReferential::getPassword);
  }

}
