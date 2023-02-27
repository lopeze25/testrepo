package com.weldingco.welding.controller;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.web.client.RestTemplate;
import com.weldingco.welding.entity.Order;
import com.weldingco.welding.entity.Equipment;
import org.junit.jupiter.api.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:resources/flyway/migrations/V1.0__Welding_Schema.sql",
    "classpath:resources/flyway/migrations/V1.1__Welding_Data.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))

class CreateOrderTest {
  @LocalServerPort
  private int serverPort;
  
  @Autowired
 // private JdbcTemplate jdbcTemplate;
  private TestRestTemplate restTemplate;
  
  @Test
  void testCreateOrderReturnsSuccess201() {
    String uri = String.format("http://localhost:%d/orders", serverPort);
    String jsonBody = "{\n"
        + "  \"customer\":\"MORISON_LINA\",\n"
        + "  \"material\":\"Aluminum Sheet\",\n"
        + "  \"procedure\":\"MIG\"\n"
        + "}";
    
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
   
    HttpEntity<String> bodyEntity = new HttpEntity<>(jsonBody, headers);
    ResponseEntity<Order> response = restTemplate.exchange(uri,
        HttpMethod.POST, bodyEntity, Order.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
  Order order = response.getBody();
  System.out.println(order + "\n"+ response);
 assertThat(order.getCustomer().getCustomerId()).isEqualTo("MORISON_LINA");
 assertThat(order.getMaterial().getMaterialId()).isEqualTo("Aluminum Sheet");
 assertThat(order.getProcedure().getProcedureId()).isEqualTo("MIG");

  }

}
