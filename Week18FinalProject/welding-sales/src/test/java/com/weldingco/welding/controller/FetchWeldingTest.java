package com.weldingco.welding.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.http.HttpMethod;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
import com.weldingco.welding.entity.Equipment;
import com.weldingco.welding.entity.Material;
import com.weldingco.welding.entity.Procedure;
import org.junit.jupiter.api.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:resources/flyway/migrations/V1.0__Welding_Schema.sql",
    "classpath:resources/flyway/migrations/V1.1__Welding_Data.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))

class FetchWeldingTest {
  protected List<Equipment> buildEquipment() {
    List<Equipment> list = new LinkedList<>();
    // @formatter:off
    list.add(Equipment.builder()
        .equipmentPK((long) 2)
        .equipmentId("Weldingrod")
        .equipmentType("E6013")
        .quantity((long) 3)
        .price(BigDecimal.valueOf(10.55))
        .build());
    return list;
  }
  protected List<Material> buildMaterial() {
    List<Material> list = new LinkedList<>();
    // @formatter:off
    list.add(Material.builder()
        .materialPK((long) 2)
        .materialId("Aluminum Sheet")
        .specifications("1/8 inch sheet")
        .composition("6061 alloy")
        .price(BigDecimal.valueOf(80.55))
        .quantity((long) 10)
        .build());
    return list;
  }
  protected List<Procedure> buildProcedure() {
    List<Procedure> list = new LinkedList<>();
    // @formatter:off
    list.add(Procedure.builder()
        .procedurePK((long) 1)
        .procedureId("MIG")
        .build());
    return list;
  }

 
  @LocalServerPort
  private int serverPort;
  
  @Autowired
  private TestRestTemplate restTemplate; 
 // private JdbcTemplate jdbcTemplate;
// **A Test jdbcTemplate to see if fetching is working;** 
 // @Test
 // void testFetch() {
 //   int numrows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "customers");
 //   System.out.println("num" + numrows);
 // }
  
  // Go to http://localhost:8080/welding?equipmentId=Wire&equipmentType=Steel 
  @Test
  void testThatEquipmentIsReturnedWhenAValidIdAndTypeAreSupplied() {
    String equipmentId = "Weldingrod";
    String equipmentType = "E6013";
   // String encodedEquipmentId = URLEncoder.encode(equipmentId, StandardCharsets.UTF_8).replace("+", "%20");
    String uri = String.format("http://localhost:%d/welding/equipment?equipmentId=%s&equipmentType=%s", serverPort, equipmentId, equipmentType);
    System.out.println(uri);
   ResponseEntity<List<Equipment>> response = 
        restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {} );
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
   List<Equipment> actual = response.getBody();
   List<Equipment> expected = buildEquipment();
   System.out.println(expected);
   assertThat(actual).isEqualTo(expected);
  }
  
  @Test
 void testThatMaterialIsReturnedWhenAValidIdAndCompositionAreSupplied() {
     String materialId = "Aluminum Sheet";
     String composition = "6061 alloy";
    String encodedmaterialId = URLEncoder.encode(materialId, StandardCharsets.UTF_8).replace(" ", "%20");
    String encodedcomposition = URLEncoder.encode(composition, StandardCharsets.UTF_8).replace(" ", "%20");
     String uri = String.format("http://localhost:%d/welding/material?materialId=%s&composition=%s", serverPort, encodedmaterialId, encodedcomposition);
     System.out.println(uri);
    ResponseEntity<List<Material>> response = 
         restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {} );
     assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    List<Material> actual = response.getBody();
    List<Material> expected = buildMaterial();
    System.out.println(expected);
    assertThat(actual).isEqualTo(expected);
   }
   
  @Test
    void testThatProcedureWhenAValidIDIsSupplied() {
      String procedureId = "MIG";
      String uri = String.format("http://localhost:%d/welding/procedure?procedureId=%s", serverPort, procedureId);
      System.out.println(uri);
     ResponseEntity<List<Procedure>> response = 
          restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {} );
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    List<Procedure> actual = response.getBody();
    List<Procedure> expected = buildProcedure();
     System.out.println(expected);
     assertThat(actual).isEqualTo(expected); 
  }
}