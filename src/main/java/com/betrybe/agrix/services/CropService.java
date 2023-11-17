package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.repositories.CropRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Crop service class.
 */
@Service
public class CropService {
  private final CropRepository cropRepository;

  public CropService(CropRepository cropRepository) {
    this.cropRepository = cropRepository;
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }
}
