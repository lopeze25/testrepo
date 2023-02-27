package com.weldingco.welding.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Procedure {
  private Long procedurePK;
  private String procedureId;
 
}
