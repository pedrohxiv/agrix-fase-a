package com.betrybe.agrix.models.controllers;

import com.betrybe.agrix.models.controllers.dto.FarmDto;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
