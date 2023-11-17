package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Crop;

/** Crop DTO record. */
public record CropDto(Long id, String name, Double plantedArea, Long farmId) {
  public Crop toCrop() {
    return new Crop(id, name, plantedArea, null);
  }
}
