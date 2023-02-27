package com.weldingco.welding.entity;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
  private Long orderPK;
  private Customer customer;
  private Material material;
  private Procedure procedure;

 @JsonIgnore
 public Long getOrderPK() {
  return orderPK;
  }
}
