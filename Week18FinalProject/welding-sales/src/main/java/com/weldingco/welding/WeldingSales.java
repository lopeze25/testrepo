package com.weldingco.welding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.weldingco.welding.entity.Customer;
import com.weldingco.welding.entity.Material;
import com.weldingco.welding.entity.Procedure;


// Welcome to Ezekiel's Welding Project 
// Swagger UI link: http://localhost:8080/swagger-ui/index.html#/default-welding-order-controller 
// Group members: None (Solo Project) 

// About the Application: API for a Welding Business to track Orders.
// Each order has a Customer, a material the order used or was performed on, and a procedure/process used to perform the job. 
// Order_equipment is a join table that tracks equipment.

// 
@SpringBootApplication
public class WeldingSales {

  public static void main(String[] args) {
    SpringApplication.run(WeldingSales.class, args);
    Terminated(); 
  }

  private static void Terminated() {
    System.out.println("This is a test");
 
  }
}
