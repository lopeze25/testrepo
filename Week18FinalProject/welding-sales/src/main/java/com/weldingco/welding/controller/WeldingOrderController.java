package com.weldingco.welding.controller;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.weldingco.welding.entity.Equipment;
import com.weldingco.welding.entity.Material;
import com.weldingco.welding.entity.Order;
import com.weldingco.welding.entity.OrderEquipment;
import com.weldingco.welding.entity.OrderRequest;
import com.weldingco.welding.entity.Procedure;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/orders")
@OpenAPIDefinition(info = @Info(title = "Weld Order Service"), servers = 
{@Server(url = "http://localhost:8080", description = "Local server.")})
    
public interface WeldingOrderController {
  // @formatter: off
  @Bean
  @Operation(
      summary = "Create an order for a Welding order",
      description = "Returns the created weld",
      responses = {
          @ApiResponse(responseCode = "200", description = "The created weld is returned", 
              content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))),
          @ApiResponse(responseCode = "400", description = "The request parameters are invalid", 
          content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", description = "A weld component was not found with the input criteria", 
          content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", description = "An unplanned error occured", 
          content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "orderRequest", required = true, 
              description = "The order as JSON"),
 
      }
   )
  // CREATE operation for orders
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Order createOrder(@RequestBody OrderRequest orderRequest);
  
// READ operation for orders
  @GetMapping("/get/{order_id}")
  @ResponseStatus(code = HttpStatus.OK)
  Order getOrder(@PathVariable("order_id") Long orderId);
  
  //UPDATE operation for orders
  @PutMapping("/update/{order_id}")
  Order updateOrder(@PathVariable("order_id") Long orderId, @RequestBody Order order);

  // DELETE operation for orders
  @DeleteMapping("/delete/{order_id}")
  Order deleteOrder(@PathVariable("order_id") Long orderId);
  
  // OrderEquipment Get
  @GetMapping("/orderEquipment")
  @ResponseStatus(code = HttpStatus.OK)
List<OrderEquipment> fetchOrderEquipment(@RequestParam(required=false) Order order, Equipment equipment );
  
}