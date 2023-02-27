package com.weldingco.welding.entity;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Material {
  private Long materialPK;
  private String materialId;
  private String specifications;
  private String composition;
  private BigDecimal price;
  private Long quantity;
}
