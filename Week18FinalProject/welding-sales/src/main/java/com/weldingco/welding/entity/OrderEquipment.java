package com.weldingco.welding.entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderEquipment {
  private Order order;
  private Equipment equipment;
  private boolean expended;
}
