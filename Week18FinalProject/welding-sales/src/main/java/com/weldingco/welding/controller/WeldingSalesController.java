package com.weldingco.welding.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import com.weldingco.welding.entity.Equipment;
import com.weldingco.welding.entity.Image;
import com.weldingco.welding.entity.Material;
import com.weldingco.welding.entity.Procedure;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@RequestMapping("/welding")
@OpenAPIDefinition(info = @Info(title = "Welds Sales Service"), servers = 
{@Server(url = "http://localhost:8080", description = "Local server.")})
    
public interface WeldingSalesController {
  @Bean
  @Operation(
      summary = "Returns a list of Equipment",
      description = "Returns a list of Equipment given a IDd and/or type",
      responses = {
          @ApiResponse(responseCode = "200", description = "A list of Equipment is returned", 
              content = @Content(mediaType = "application/json", schema = @Schema(implementation = Equipment.class))),
          @ApiResponse(responseCode = "400", description = "The request parameters are invalid", 
          content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", description = "No Equipment were found with the input criteria", 
          content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", description = "An unplanned error occured", 
          content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "equipmentId", allowEmptyValue = false, required = false, 
              description = "The equipmentId/name"),
          @Parameter(name = "equipmentType", allowEmptyValue = false, required = false, 
              description = "The equipmentType level")
      }
   )
  
  // Project requirements at least at least 3 entities should have one CRUD operationss
  @GetMapping("/equipment")
  @ResponseStatus(code = HttpStatus.OK)
  List<Equipment> fetchEquipment(@RequestParam(required=false) String equipmentId, @RequestParam(required=false)String equipmentType);
  
  @GetMapping("/material")
  @ResponseStatus(code = HttpStatus.OK)
List<Material> fetchMaterial(@RequestParam(required=false) String materialId, @RequestParam(required=false)String composition);

  @GetMapping("/procedure")
  @ResponseStatus(code = HttpStatus.OK)
List<Procedure> fetchProcedure(@RequestParam(required=false) String procedureId);

  @PostMapping("/procedure/{procedurePK}/image")
  @ResponseStatus(code = HttpStatus.CREATED)
 String uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Long procedurePK);
  
  @GetMapping("/procedure/image/{imageId}")
  @ResponseStatus(code = HttpStatus.CREATED)
  ResponseEntity<byte[]> retrieveImage(@PathVariable String imageId);
}

