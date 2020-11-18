package org.orion.region.service;

import java.util.List;
import org.orion.region.error.EntityNotFoundException;
import org.orion.region.model.Region;

public interface RegionService {

  Region create(Region region);

  Region update(Region region);

  boolean delete(int id);

  Region getById(int id) throws EntityNotFoundException;

  Region getByCode(String code) throws EntityNotFoundException;

  List<Region> getAll();
  List<Region> getByPattern(String  pattern);
}
