package com.betrybe.agrix.models.controllers.dto;

import com.betrybe.agrix.models.entities.Farm;

/**
 * Farm DTO record.
 */
public record FarmDto(Long id, String name, Double size) {
  public Farm toFarm() {
    return new Farm(id, name, size);
  }
}
