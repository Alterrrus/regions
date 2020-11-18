package org.orion.region.service;

import java.util.List;
import org.orion.region.error.EntityNotFoundException;
import org.orion.region.model.Region;
import org.orion.region.repo.RegionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl implements RegionService {

  private final RegionRepo repo;

  @Autowired
  public RegionServiceImpl(RegionRepo repo) {
    this.repo = repo;
  }

  @Override
  @CacheEvict(value = "regions", allEntries = true)
  public Region create(Region region) {
    return repo.create(region);
  }

  @Override
  @CacheEvict(value = "regions", allEntries = true)
  public Region update(Region region) {

    return repo.update(region);

  }

  @Override
  @CacheEvict(value = "regions", allEntries = true)
  public boolean delete(int id) {
    return repo.delete(id);
  }

  @Override
  @Cacheable(value = "regions")
  public Region getById(int id) throws EntityNotFoundException {

    return repo.getById(id);
  }

  @Override
  @Cacheable(value = "regions")
  public Region getByCode(String code) throws EntityNotFoundException {

    return repo.getByCode(code);
  }

  @Override
  @Cacheable("regions")
  public List<Region> getAll() {
    return repo.getAll();
  }

  @Override
  public List<Region> getByPattern(String pattern) {
    return repo.getByPattern(pattern);
  }

}
