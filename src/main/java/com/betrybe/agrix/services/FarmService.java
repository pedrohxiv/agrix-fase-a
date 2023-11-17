package com.betrybe.agrix.services;

import com.betrybe.agrix.controllers.exceptions.NotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Farm service class.
 */
@Service
public class FarmService {
  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  public Optional<Farm> getFarmById(Long farmId) {
    return farmRepository.findById(farmId);
  }

  /**
   * Create crop.
   *
   * @param farmId Long
   * @param crop   Crop
   * @return newCrop
   */
  public Optional<Crop> createCrop(Long farmId, Crop crop) {
    Optional<Farm> optionalFarm = farmRepository.findById(farmId);

    if (optionalFarm.isEmpty()) {
      return Optional.empty();
    }

    Farm farm = optionalFarm.get();
    crop.setFarm(farm);
    Crop newCrop = cropRepository.save(crop);
    farm.getCrops().add(newCrop);

    return Optional.of(newCrop);
  }

  /**
   * Get crops by farm id.
   *
   * @param farmId Long
   * @return crops
   */
  public Optional<List<Crop>> getAllCropsByFarmId(Long farmId) {
    Optional<Farm> optionalFarm = farmRepository.findById(farmId);

    if (optionalFarm.isEmpty()) {
      return Optional.empty();
    }

    Farm farm = optionalFarm.get();

    return Optional.of(farm.getCrops());
  }
}
