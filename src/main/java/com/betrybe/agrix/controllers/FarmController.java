package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.controllers.exceptions.NotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Farm controller class.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {
  private final FarmService farmService;

  /**
   * Constructor.
   *
   * @param farmService FarmService
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Farm POST method.
   *
   * @param farmDto FarmDto
   * @return ResponseEntity
   */
  @PostMapping()
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmService.createFarm(farmDto.toFarm());

    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  /**
   * Farm GET method.
   *
   * @return ResponseEntity
   */
  @GetMapping()
  public List<FarmDto> getAllFarms() {
    List<Farm> allFarms = farmService.getAllFarms();

    return allFarms.stream()
        .map(farm -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
        .collect(Collectors.toList());
  }

  /**
   * Farm GET method.
   *
   * @param farmId Long
   * @return ResponseEntity
   */
  @GetMapping(value = "/{farmId}")
  public ResponseEntity<Farm> getFarmById(@PathVariable Long farmId) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new NotFoundException("Fazenda não encontrada!");
    }

    return ResponseEntity.status(HttpStatus.OK).body(optionalFarm.get());
  }

  /**
   * Crop POST method.
   *
   * @param farmId  Long
   * @param cropDto CropDto
   * @return ResponseEntity
   */
  @PostMapping(value = "/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(
      @PathVariable Long farmId,
      @RequestBody CropDto cropDto) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new NotFoundException("Fazenda não encontrada!");
    }

    Crop newCrop = farmService.createCrop(farmId, cropDto.toCrop()).get();

    return ResponseEntity.status(HttpStatus.CREATED).body(new CropDto(
        newCrop.getId(),
        newCrop.getName(),
        newCrop.getPlantedArea(),
        newCrop.getFarm().getId()));
  }

  /**
   * Crop GET method.
   *
   * @param farmId Long
   * @return ResponseEntity
   */
  @GetMapping(value = "/{farmId}/crops")
  public List<CropDto> getAllCropsByFarmId(@PathVariable Long farmId) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new NotFoundException("Fazenda não encontrada!");
    }

    List<Crop> crops = farmService.getAllCropsByFarmId(farmId).get();

    return crops.stream()
        .map(crop -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getFarm().getId()))
        .collect(Collectors.toList());
  }

  /**
   * Exception handler.
   *
   * @param exception NotFoundException
   * @return ResponseEntity
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }
}
