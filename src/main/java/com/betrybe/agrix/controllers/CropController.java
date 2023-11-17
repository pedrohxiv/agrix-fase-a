package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.services.CropService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crop controller class.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {
  private final CropService cropService;

  /**
   * Constructor.
   *
   * @param cropService CropService
   */
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Crop GET method.
   *
   * @return ResponseEntity
   */
  @GetMapping()
  public List<CropDto> getAllCrops() {
    List<Crop> allCrops = cropService.getAllCrops();

    return allCrops.stream()
        .map(crop -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getFarm().getId()))
        .collect(Collectors.toList());
  }
}
