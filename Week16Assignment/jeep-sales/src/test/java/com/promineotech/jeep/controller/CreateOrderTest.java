package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.jeep.controller.support.CreateOrderTestSupport;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Order;
import springfox.documentation.spring.web.json.Json;
import static org.assertj.core.api.Assertions.assertThat;
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
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:resources/flyway/migrations/V1.0__Jeep_Schema.sql",
    "classpath:resources/flyway/migrations/V1.1__Jeep_Data.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))
class CreateOrderTest extends CreateOrderTestSupport {
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
            + "  \"model\":\"WRANGLER\",\n"
            + "  \"trim\":\"Sport Altitude\",\n"
            + "  \"doors\":4,\n"
            + "  \"color\":\"EXT_NACHO\",\n"
            + "  \"engine\":\"2_0_TURBO\",\n"
            + "  \"tire\":\"35_TOYO\",\n"
            + "  \"options\":[\n"
            + "    \"DOOR_QUAD_4\",\n"
            + "    \"EXT_AEV_LIFT\",\n"
            + "    \"EXT_WARN_WINCH\",\n"
            + "    \"EXT_WARN_BUMPER_FRONT\",\n"
            + "    \"EXT_WARN_BUMPER_REAR\",\n"
            + "    \"EXT_ARB_COMPRESSOR\"\n"
            + "  ]\n"
            + "}";

 
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
   
    HttpEntity<String> bodyEntity = new HttpEntity<>(jsonBody, headers);
    ResponseEntity<Order> response = restTemplate.exchange(uri,
        HttpMethod.POST, bodyEntity, Order.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isNotNull();

    Order order = response.getBody();
    assertThat(order.getCustomer().getCustomerId()).isEqualTo("MORISON_LINA");
   assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.WRANGLER);
   assertThat(order.getModel().getTrimLevel()).isEqualTo("Sport Altitude");
   assertThat(order.getModel().getNumDoors()).isEqualTo(4);
   assertThat(order.getColor().getColorId()).isEqualTo("EXT_NACHO");
   assertThat(order.getEngine().getEngineId()).isEqualTo("2_0_TURBO");
   assertThat(order.getTire().getTireId()).isEqualTo("35_TOYO");
   assertThat(order.getOptions()).hasSize(6);

  }
  
  

  protected String createOrderBody() {
    // @formatter:off  
    return "{\r\n"
    + "  \"customer\":\"MORISON_LINA\",\r\n"
    + "  \"model\":\"WRANGLER\",\r\n"
    + "  \"trim\":\"Sport Altitude\",\r\n"
    + "  \"doors\":4,\r\n"
    + "  \"color\":\"EXT_NACHO\",\r\n"
    + "  \"engine\":\"2_0_TURBO\",\r\n"
    + "  \"tire\":\"35_TOYO\",\r\n"
    + "  \"options\":[\r\n"
    + "    \"DOOR_QUAD_4\",\r\n"
    + "    \"EXT_AEV_LIFT\",\r\n"
    + "    \"EXT_WARN_WINCH\",\r\n"
    + "    \"EXT_WARN_BUMPER_FRONT\",\r\n"
    + "    \"EXT_WARN_BUMPER_REAR\",\r\n"
    + "    \"EXT_ARB_COMPRESSOR\"\r\n"
    + "  ]\r\n"
    + "}\r\n"
    + "";
    // @formatter:on
 
  }
  
}


