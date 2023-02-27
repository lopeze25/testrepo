package com.weldingco.welding.service;

import java.util.List;
import com.weldingco.welding.entity.Equipment;
import com.weldingco.welding.entity.Order;
import com.weldingco.welding.entity.OrderEquipment;
import com.weldingco.welding.entity.OrderRequest;
import com.weldingco.welding.entity.Procedure;

public interface WeldingOrderService {

  Order createOrder(OrderRequest orderRequest);

  Order deleteOrder(Long orderId);

  Order updateOrder(Long orderId, Order order);

  Order getOrder(Long orderId);
  
  List<OrderEquipment> fetchOrderEquipment(Order order, Equipment equipment);

}
