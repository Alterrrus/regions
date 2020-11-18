package org.orion.region.repo;

import java.util.List;
import org.orion.region.model.Region;

public interface RegionRepo {

  Region create(Region region);
  Region update(Region region);
  boolean delete(int id);
  List<Region> getAll();
  List<Region> getByPattern(String pattern);
  Region getById(int id);
  Region getByCode(String code);


}
