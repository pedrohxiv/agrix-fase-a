package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.repositories.CropRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Crop service class. */
@Service
public class CropService {
  /** Attributes. */
  private final CropRepository cropRepository;

  /** Constructor method. */
  @Autowired
  public CropService(CropRepository cropRepository) {
    this.cropRepository = cropRepository;
  }

  /** Get all crops method. */
  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /** Get crop by ID method. */
  public Optional<Crop> getCropById(Long cropId) {
    return cropRepository.findById(cropId);
  }
}
