package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.http.HttpMethod;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.web.client.RestTemplate;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:resources/flyway/migrations/V1.0__Jeep_Schema.sql",
    "classpath:resources/flyway/migrations/V1.1__Jeep_Data.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))
class FetchJeepTest {
  protected List<Jeep> buildExpected() {
    List<Jeep> list = new LinkedList<>();
    // @formatter:off
    list.add(Jeep.builder()
        .modelId(JeepModel.WRANGLER)
        .trimLevel("Sport")
        .numDoors(2)
        .wheelSize(17)
        .basePrice(new BigDecimal("26475.00"))
        .build());
    
    list.add(Jeep.builder()
        .modelId(JeepModel.WRANGLER)
        .trimLevel("Sport")
        .numDoors(4)
        .wheelSize(17)
        .basePrice(new BigDecimal("31975.00"))
        .build());
    // @formatter:on
    return list;
  }
  
  @LocalServerPort
  private int serverPort;
  
  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Test
  void testDb() {
    int numrows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "customers");
    System.out.println("num" + numrows);
  }
  
  void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
    JeepModel model = JeepModel.WRANGLER;
    String trim = "Sport";
    String uri =
      String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);
    System.out.println(uri);
    
    ResponseEntity<List<Jeep>> response = 
        restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {} );
    List<Jeep> expected = buildExpected();
    System.out.println(expected);
    assertThat(response.getBody()).isEqualTo(expected);

  }
}

