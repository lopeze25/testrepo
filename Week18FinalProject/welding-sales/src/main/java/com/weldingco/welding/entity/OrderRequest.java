package com.weldingco.welding.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OrderRequest {
  private String customer;
  private String material;
  private String procedure;
}

