package com.weldingco.welding.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
import com.weldingco.welding.dao.DefaultWeldingOrderDao.SqlParams;
import com.weldingco.welding.entity.Customer;
import com.weldingco.welding.entity.Equipment;
import com.weldingco.welding.entity.Image;
import com.weldingco.welding.entity.ImageMimeType;
import com.weldingco.welding.entity.Material;
import com.weldingco.welding.entity.Order;
import com.weldingco.welding.entity.Procedure;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.schema.Maps;

@Component
@Slf4j
public class DefaultWeldingSalesDao implements WeldingSalesDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  
  
  @Override
  public List<Equipment> fetchEquipment(String equipmentId, String equipmentType) {
    log.info("DAO: equipmentId={} and equipmentType={}", equipmentId, equipmentType);
    String sql = ""
        + "SELECT * "
        + "FROM equipment "
        + "WHERE equipment_id = :equipment_id AND equipment_type = :equipment_type";
        
        Map<String,Object> params = new HashMap<>();
        params.put("equipment_id", equipmentId);
        params.put("equipment_type", equipmentType);
    return jdbcTemplate.query(sql, params,
        new RowMapper<>() {
    @Override
          public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException {
            // TODO Auto-generated method stub
            return Equipment.builder()
                .equipmentPK(rs.getLong("equipment_pk")) 
                .equipmentId(rs.getString("equipment_id"))
                .equipmentType(rs.getString("equipment_type"))   
                .quantity(rs.getLong("quantity"))
                .price(new BigDecimal(rs.getString("price")))
                .build();
          }} );
  }
  
  public List<Material> fetchMaterial(String materialId, String composition) {
    log.info("DAO: materialId={} and composition={}", materialId, composition);
    String sql = ""
        + "SELECT * "
        + "FROM materials "
        + "WHERE material_id = :material_id AND composition = :composition";
        
        Map<String,Object> params = new HashMap<>();
        params.put("material_id", materialId);
        params.put("composition", composition);
    return jdbcTemplate.query(sql, params,
        new RowMapper<>() {
    @Override
          public Material mapRow(ResultSet rs, int rowNum) throws SQLException {
            // TODO Auto-generated method stub
            return Material.builder()
                .materialPK(rs.getLong("material_pk")) 
                .materialId(rs.getString("material_id"))
                .specifications(rs.getString("specifications")) 
                .composition(rs.getString("composition")) 
                .quantity(rs.getLong("quantity"))
                .price(new BigDecimal(rs.getString("price")))
                .build();
          }} );
  }
  
  public List<Procedure> fetchProcedure(String procedureId) {
    log.info("DAO: procedureId={}", procedureId);
    String sql = ""
        + "SELECT * "
        + "FROM procedures "
        + "WHERE procedure_id = :procedure_id";
        
        Map<String,Object> params = new HashMap<>();
        params.put("procedure_id", procedureId);
    return jdbcTemplate.query(sql, params,
        new RowMapper<>() {
    @Override
          public Procedure mapRow(ResultSet rs, int rowNum) throws SQLException {
            // TODO Auto-generated method stub
            return Procedure.builder()
                .procedurePK(rs.getLong("procedure_pk")) 
                .procedureId(rs.getString("procedure_id"))
                .build();
          }} );
  }

   
   @Override
   public void saveImage(Image image) {
     String sql = "INSERT INTO images (image_id, procedure_fk, width, height, mime_type, name, data) VALUES (:image_id, :procedure_fk, :width, :height, :mime_type, :name, :data)";
     Map<String, Object> params = new HashMap<>();
     params.put("procedure_fk", image.getProcedurePK());
     params.put("image_id", image.getImageId());
     params.put("width",image.getWidth());
     params.put("height",image.getHeight());
     params.put("mime_type", image.getMimeType().getMimeType());
     params.put("name", image.getName());
     params.put("data", image.getData());
     jdbcTemplate.update(sql, params);
     
}

  @Override
  public Optional<Image> retrieveImage(String imageId) {
    String sql = 
        "SELECT * FROM images WHERE image_id = :image_id";
    Map<String, Object> params = new HashMap<>();
    params.put("image_id", imageId);
    return jdbcTemplate.query(sql, params, new ResultSetExtractor<>() {
      
      @Override
      public Optional<Image> extractData(ResultSet rs) throws SQLException {
        if(rs.next()) {
          return Optional.of(Image.builder()
              .imagePK(rs.getLong("image_pk"))
              .procedurePK(rs.getLong("procedure_fk"))
              .imageId(rs.getString("image_id"))
              .width(rs.getInt("width"))
              .height(rs.getInt("height"))
              .mimeType(ImageMimeType.fromString(rs.getString("mime_type")))
              .name(rs.getString("name"))
              .data(rs.getBytes("data"))
              .build());
        }
        return Optional.empty();
      }});
      
  }
  
  
}

