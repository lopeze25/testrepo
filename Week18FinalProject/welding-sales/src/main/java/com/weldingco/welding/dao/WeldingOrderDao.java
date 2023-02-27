package com.weldingco.welding.dao;

import java.util.List;
import java.util.Optional;
import com.weldingco.welding.entity.Customer;
import com.weldingco.welding.entity.Equipment;
import com.weldingco.welding.entity.Material;
import com.weldingco.welding.entity.Order;
import com.weldingco.welding.entity.OrderEquipment;
import com.weldingco.welding.entity.OrderRequest;
import com.weldingco.welding.entity.Procedure;


public interface WeldingOrderDao {
  Optional<Customer> fetchCustomer(String customerId);
  Optional<Material> fetchMaterial(String materialId);
  Optional<Procedure> fetchProcedure(String procedureId);
  Order saveOrder(Customer customer, Material material, Procedure procedure);
  Order createOrder(OrderRequest orderRequest);
  Order deleteOrder(Long orderId);
  Order updateOrder(Long orderId, Order order);
  Order getOrder(Long orderId);
  List<OrderEquipment> fetchOrderEquipment(Order order, Equipment equipment);
}


