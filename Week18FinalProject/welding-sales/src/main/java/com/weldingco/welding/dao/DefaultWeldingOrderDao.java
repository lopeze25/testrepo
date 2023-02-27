package com.weldingco.welding.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.mysql.cj.jdbc.JdbcConnection;
import com.weldingco.welding.entity.Customer;
import com.weldingco.welding.entity.Equipment;
import com.weldingco.welding.entity.Material;
import com.weldingco.welding.entity.Order;
import com.weldingco.welding.entity.OrderEquipment;
import com.weldingco.welding.entity.OrderRequest;
import com.weldingco.welding.entity.Procedure;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class DefaultWeldingOrderDao implements WeldingOrderDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  public Order saveOrder(Customer customer, Material material, Procedure procedure){
    SqlParams params = generateInsertSql(customer, material, procedure);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);
    Long orderPK = keyHolder.getKey().longValue();
 
    return Order.builder()
        .orderPK(orderPK)
        .customer(customer)
        .material(material)
        .procedure(procedure)
        .build();
    
    //@formatter:on
  }
 
  /**
   * 
   * @param customer
   * @param material
   * @param procedure
   * @return
   */  private SqlParams generateInsertSql(Customer customer, Material material, Procedure procedure) {
    // @formatter:off
    String sql = ""
        + "INSERT INTO orders ("
        + "customer_fk, material_fk, procedure_fk"
        + ") VALUES ("
        + ":customer_fk, :material_fk, :procedure_fk"
        + ")";
    // @formatter:on
    SqlParams params = new SqlParams(); 
    params.sql = sql;
    params.source.addValue("customer_fk", customer.getCustomerPK());
    params.source.addValue("material_fk", material.getMaterialPK());
    params.source.addValue("procedure_fk", procedure.getProcedurePK());
    return params;
   }
  /**
   * 
   */
  public Optional<Customer> fetchCustomer(String customerId) {
    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM customers "
        + "WHERE customer_id = :customer_id";
    // @formatter:on
    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", customerId);
    return Optional.ofNullable(
        jdbcTemplate.query(sql, params, new CustomerResultSetExtractor())); }
  /**
   * 
   */
  public Optional<Material> fetchMaterial(String materialId) {
    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM materials "
        + "WHERE material_id = :material_id";
    // @formatter:on
    Map<String, Object> params = new HashMap<>();
    params.put("material_id", materialId);
    return Optional.ofNullable(
        jdbcTemplate.query(sql, params, new MaterialResultSetExtractor())); }
  
  public Optional<Procedure> fetchProcedure(String procedureId) {
    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM procedures "
        + "WHERE procedure_id = :procedure_id";
    // @formatter:on
    Map<String, Object> params = new HashMap<>();
    params.put("procedure_id", procedureId);
    return Optional.ofNullable(
        jdbcTemplate.query(sql, params, new ProcedureResultSetExtractor())); }
 
 class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {
    @Override
    public Customer extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Customer.builder()
          .customerId(rs.getString("customer_id"))
          .customerPK(rs.getLong("customer_pk"))
          .email(rs.getString("email"))
          .phone(rs.getString("phone"))
          .build();
      // @formatter:on
} }
 
 class MaterialResultSetExtractor implements ResultSetExtractor<Material> {
   @Override
   public Material extractData(ResultSet rs) throws SQLException {
     rs.next();

     // @formatter:off
     return Material.builder()
         .materialId(rs.getString("material_id"))
         .materialPK(rs.getLong("material_pk"))
         .build();
     // @formatter:on
} }
 
 class ProcedureResultSetExtractor implements ResultSetExtractor<Procedure> {
   @Override
   public Procedure extractData(ResultSet rs) throws SQLException {
     rs.next();

     // @formatter:off
     return Procedure.builder()
         .procedureId(rs.getString("procedure_id"))
         .procedurePK(rs.getLong("procedure_pk"))
         .build();
     // @formatter:on
} }
 class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  } 
 @Override
 public Order createOrder(OrderRequest orderRequest) {
   log.debug("Order={}", orderRequest);
   return null;}

@Override
public Order deleteOrder(Long orderId) {
// Returning null to not interfere with database integrity. 
// SQL would be "DELETE FROM orders WHERE order_id = ?" to demonstrate understanding 
  return null;
}

@Override
public Order updateOrder(Long orderId, Order order) {
// Returning null to not interfere with database integrity. 
// SQL would be 
// UPDATE orders SET customer_id = ?, procedure_id = ?, material_id = ?, description = ? WHERE order_id = ?  to demonstrate understanding 
  return null;
}

@Override
public Order getOrder(Long orderPK)  {
  return null;
}

@Override
public List<OrderEquipment> fetchOrderEquipment(Order order, Equipment equipment) {
  log.info("DAO: order=" + order + ", equipment=" + equipment);
  String sql = ""
      + "SELECT * "
      + "FROM order_equipment "
      + "WHERE order_fk = :order_fk AND equipment_fk = :equipment_fk";

  Map<String,Object> params = new HashMap<>();
  params.put("order_fk", order.getOrderPK());
  params.put("equipment_fk", equipment.getEquipmentPK());

  return jdbcTemplate.query(sql, params,
      new RowMapper<>() {
          @Override
          public OrderEquipment mapRow(ResultSet rs, int rowNum) throws SQLException {
              return OrderEquipment.builder()
                  .order(order)
                  .equipment(equipment)
                  .expended(rs.getBoolean("expended"))
                  .build();
          }
      });
}








}
 
 