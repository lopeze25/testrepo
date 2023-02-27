package com.weldingco.welding.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.weldingco.welding.dao.WeldingOrderDao;
import com.weldingco.welding.entity.Customer;
import com.weldingco.welding.entity.Equipment;
import com.weldingco.welding.entity.Material;
import com.weldingco.welding.entity.Order;
import com.weldingco.welding.entity.OrderEquipment;
import com.weldingco.welding.entity.OrderRequest;
import com.weldingco.welding.entity.Procedure;


@Service
public class DefaultWeldingOrderService implements WeldingOrderService {
  @Autowired
  private WeldingOrderDao weldingOrderDao;
  
  @Transactional
  @Override
  public Order createOrder(OrderRequest orderRequest) {
    Customer customer = getCustomer(orderRequest);
    Material material = getMaterial(orderRequest);
    Procedure procedure = getProcedure(orderRequest);
 
    return weldingOrderDao.saveOrder(customer, material, procedure);
  } 

  /**
   * 
   * @param orderRequest
   * @return
   */ private Customer getCustomer(OrderRequest orderRequest) {
    return weldingOrderDao.fetchCustomer(orderRequest.getCustomer())
        .orElseThrow(() -> new NoSuchElementException("Customer with ID="
            + orderRequest.getCustomer() + " was not found")); }
   private Material getMaterial(OrderRequest orderRequest) {
     return weldingOrderDao.fetchMaterial(orderRequest.getMaterial())
         .orElseThrow(() -> new NoSuchElementException("Material with ID="
             + orderRequest.getMaterial() + " was not found")); }
   private Procedure getProcedure(OrderRequest orderRequest) {
     return weldingOrderDao.fetchProcedure(orderRequest.getProcedure())
         .orElseThrow(() -> new NoSuchElementException("Procedure with ID="
             + orderRequest.getProcedure() + " was not found")); }

  @Override
  public Order deleteOrder(Long orderId) {
   
    return weldingOrderDao.deleteOrder(orderId);
  }

  @Override
  public Order updateOrder(Long orderId, Order order) {
    // TODO Auto-generated method stub
    return weldingOrderDao.updateOrder(orderId, order);
  }

  @Override
  public Order getOrder(Long orderId) {
    // TODO Auto-generated method stub
    return weldingOrderDao.getOrder(orderId);
  }

  @Override
  public List<OrderEquipment> fetchOrderEquipment(Order order, Equipment equipment) {
    return weldingOrderDao.fetchOrderEquipment(order, equipment);
  }


}