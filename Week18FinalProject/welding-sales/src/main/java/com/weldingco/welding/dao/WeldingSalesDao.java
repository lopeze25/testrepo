package com.weldingco.welding.dao;

import java.util.List;
import java.util.Optional;
import com.weldingco.welding.entity.Equipment;
import com.weldingco.welding.entity.Image;
import com.weldingco.welding.entity.Material;
import com.weldingco.welding.entity.Procedure;

public interface WeldingSalesDao {
  List<Equipment> fetchEquipment(String equipmentId, String equipmentType);
  List<Material> fetchMaterial(String materialId, String composition);
  List<Procedure> fetchProcedure(String procedureId);
  void saveImage(Image image);
  Optional<Image> retrieveImage(String imageId); 
}
