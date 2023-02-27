package com.weldingco.welding.entity;

import java.math.BigDecimal;
import java.util.Comparator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipment implements Comparable<Equipment>{
  private Long equipmentPK;
  private String equipmentId;
  private String equipmentType;
  private Long quantity;
  private BigDecimal price;
  @Override
  public int compareTo(Equipment that) {
    //@formatter:off
    return Comparator
        .comparing(Equipment::getEquipmentPK)
        .thenComparing(Equipment::getEquipmentId)
        .thenComparing(Equipment::getEquipmentType)
        .compare(this, that);
    }
}