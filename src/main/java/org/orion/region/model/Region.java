package org.orion.region.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@Data
@ToString

public class Region {

  private int id;
  private String name;
  private String code;

  public Region() {
  }

  public Region(String name, String code) {
    this.name = name;
    this.code = code;
  }

  public Region(int id, String name, String code) {
    this.id = id;
    this.name = name;
    this.code = code;
  }

}
