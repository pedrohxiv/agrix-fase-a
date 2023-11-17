package com.betrybe.agrix.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Farm entity class.
 */
@Entity
@Table(name = "farms")
public class Farm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Double size;

  /**
   * Empty constructor.
   */
  public Farm() {
  }

  /**
   * Constructor.
   *
   * @param id Long
   * @param name String
   * @param size Double
   */
  public Farm(Long id, String name, Double size) {
    this.id = id;
    this.name = name;
    this.size = size;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getSize() {
    return size;
  }

  public void setSize(Double size) {
    this.size = size;
  }
}
