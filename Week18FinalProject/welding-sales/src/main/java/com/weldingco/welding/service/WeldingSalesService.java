package com.weldingco.welding.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.weldingco.welding.entity.Equipment;
import com.weldingco.welding.entity.Image;
import com.weldingco.welding.entity.Material;
import com.weldingco.welding.entity.Procedure;

public interface WeldingSalesService {

  List<Equipment> fetchEquipment(String equipmentId, String equipmentType);

  List<Material> fetchMaterial(String materialId, String composition);

  List<Procedure> fetchProcedure(String procedureId);

  String uploadImage(MultipartFile image, Long procedurePK);

  Image retrieveImage(String imageId);

}
