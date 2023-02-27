package com.weldingco.welding.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.weldingco.welding.entity.Equipment;
import com.weldingco.welding.entity.Order;
import com.weldingco.welding.entity.OrderEquipment;
import com.weldingco.welding.entity.OrderRequest;
import com.weldingco.welding.entity.Procedure;
import com.weldingco.welding.service.WeldingOrderService;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class DefaultWeldingOrderController implements WeldingOrderController {
  
  @Autowired
  private WeldingOrderService weldingOrderService;
  @Override
  public Order createOrder(OrderRequest orderRequest) {
   log.info("Order={}", orderRequest); 
   return weldingOrderService.createOrder(orderRequest);
  }
  @Override
  public Order deleteOrder(Long orderId) {
    // TODO Auto-generated method stub
    return weldingOrderService.deleteOrder(orderId);
  }
  @Override
  public Order updateOrder(Long orderId, Order order) {
    // TODO Auto-generated method stub
    return weldingOrderService.updateOrder(orderId, order);
  }
  @Override
  public Order getOrder(Long orderId) {
    // TODO Auto-generated method stub
    return weldingOrderService.getOrder(orderId);
  }
  @Override
  public List<OrderEquipment> fetchOrderEquipment(Order order, Equipment equipment) {
    
    return weldingOrderService.fetchOrderEquipment(order, equipment);
  }


}
