package com.weldingco.welding.controller;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.weldingco.welding.entity.Customer;
import com.weldingco.welding.entity.Equipment;
import com.weldingco.welding.entity.Image;
import com.weldingco.welding.entity.Material;
import com.weldingco.welding.entity.Procedure;
import com.weldingco.welding.service.WeldingSalesService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultWeldingSalesController implements WeldingSalesController {

  @Autowired
  private WeldingSalesService weldingSalesService;
  
  @Override
  public List<Equipment> fetchEquipment(String equipmentId, String equipmentType) {
    log.info("equipmentId={}, equipmentType={}", equipmentId, equipmentType); 
    return weldingSalesService.fetchEquipment(equipmentId,equipmentType);
  }
 @Override
 public List<Material> fetchMaterial(String materialId, String composition) {
   log.info("materialId={}, composition={}", materialId, composition); 
    return weldingSalesService.fetchMaterial(materialId, composition);
  }
  @Override
  public List<Procedure> fetchProcedure(String procedureId) {
    log.info("procedureId={}", procedureId); 
    return weldingSalesService.fetchProcedure(procedureId);
  }


  @Override
  public String uploadImage(MultipartFile image, Long procedurePK) {
    log.debug("image={}, procedurePK={}", image, procedurePK);
    return weldingSalesService.uploadImage(image, procedurePK);
  }
  
  @Override
  public ResponseEntity<byte[]> retrieveImage(String imageId) {
      log.debug("Retrieving image with ID={}", imageId);

      Image image = weldingSalesService.retrieveImage(imageId);

  HttpHeaders headers = new HttpHeaders();
  headers.add("Content-Type", image.getMimeType().getMimeType());
   System.out.println("TEST"+image.getMimeType().getMimeType());
  headers.add("Content-Length", Integer.toString(image.getData().length));
  System.out.println("TEST"+Integer.toString(image.getData().length));
  System.out.println(headers);
  return ResponseEntity.ok().headers(headers).body(image.getData());
  }
}





