package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.exceptions.NotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.services.CropService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Crop controller class. */
@RestController
@RequestMapping(value = "/crops")
public class CropController {
  /** Attributes. */
  private final CropService cropService;

  /** Constructor method. */
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /** GET crops method. */
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

  /** GET crop method. */
  @GetMapping(value = "/{cropId}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long cropId) {
    Optional<Crop> optionalCrop = cropService.getCropById(cropId);

    if (optionalCrop.isEmpty()) {
      throw new NotFoundException("Plantação não encontrada!");
    }

    Crop crop = optionalCrop.get();

    return ResponseEntity.status(HttpStatus.OK).body(new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId()));
  }

  /** Exception handler. */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }
}
