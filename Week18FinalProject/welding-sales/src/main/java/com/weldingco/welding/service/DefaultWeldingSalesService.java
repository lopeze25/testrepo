package com.weldingco.welding.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.weldingco.welding.dao.WeldingSalesDao;
import com.weldingco.welding.entity.Equipment;
import com.weldingco.welding.entity.Image;
import com.weldingco.welding.entity.ImageMimeType;
import com.weldingco.welding.entity.Material;
import com.weldingco.welding.entity.Procedure;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultWeldingSalesService implements WeldingSalesService {
  
  @Autowired
  private WeldingSalesDao weldingSalesDao;
 
  @Transactional
  @Override
  public String uploadImage(MultipartFile file, Long procedurePK) {
    String imageId = UUID.randomUUID().toString();
    log.debug("Uploading image with ID={}", imageId);
    try(InputStream inputStream = file.getInputStream()){
      BufferedImage bufferedImage = ImageIO.read(inputStream);
      
      
      Image image = Image.builder()
         .procedurePK(procedurePK)
        .imageId(imageId)
        .width(bufferedImage.getWidth())
        .height(bufferedImage.getHeight())
        .mimeType(ImageMimeType.IMAGE_JPEG)
        .name(file.getOriginalFilename())
        .data(toByteArray(bufferedImage, "jpeg"))
        .build();
      weldingSalesDao.saveImage(image);
      return imageId;
    } catch (IOException e)  {
          throw new UncheckedIOException(e);
    }
  }
    
private byte[] toByteArray(BufferedImage bufferedImage, String renderType) {
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      if(!ImageIO.write(bufferedImage, renderType, baos))
        throw new RuntimeException("Could not write to image buffer");
      return baos.toByteArray();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }


  @Override
  public List<Equipment> fetchEquipment(String equipmentId, String equipmentType) {
    log.info("The fetchEquipment method was called with equipmentId={} and equipmentType={}", equipmentId, equipmentType);
    return weldingSalesDao.fetchEquipment(equipmentId, equipmentType); 
  }
  @Override
  public List<Material> fetchMaterial(String materialId, String composition) {
    log.info("The fetchMaterials method was called with materialId={} and composition={}", materialId, composition);
    return weldingSalesDao.fetchMaterial(materialId, composition);
  }
  @Override
  public List<Procedure> fetchProcedure(String procedureId) {
    log.info("The fetchProcedure method was called with procedureId={}", procedureId);
    return weldingSalesDao.fetchProcedure(procedureId);
  }
  
  @Transactional(readOnly = true)
  @Override
  public Image retrieveImage(String imageId) {
    return weldingSalesDao.retrieveImage(imageId).orElseThrow(() -> new NoSuchElementException("Could not find image with ID=" + imageId));
  }

}
