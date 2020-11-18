package org.orion.region.repo;

import java.util.List;
import org.orion.region.error.EntityNotFoundException;
import org.orion.region.mapper.MapperRegion;
import org.orion.region.model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegionRepoImpl implements RegionRepo {

  Logger log = LoggerFactory.getLogger(RegionRepoImpl.class);


  private final MapperRegion mapper;

  @Autowired
  public RegionRepoImpl(MapperRegion mapper) {
    this.mapper = mapper;
  }


  @Override
  public Region create(Region region) {
    int i = mapper.create(region);
    log.info("create " + region.toString()+" "+i);
    return getById(region.getId());
  }

  @Override
  public Region update(Region region) {
    int i = mapper.updateRegion(region);
    if(i==0) throw new EntityNotFoundException(Region.class,"id", Integer.toString(region.getId()));
    log.info("update" + region.toString()+" " +i);
    return getById(region.getId());
  }

  @Override
  public boolean delete(int id) {
    log.info("delete");
    return mapper.deleteRegion(id);
  }

  @Override
  public List<Region> getAll() {
    log.info("getAll");
    return mapper.getAll();
  }

  @Override
  public List<Region> getByPattern(String pattern) {
    return mapper.getByPattern(pattern);
  }

  @Override
  public Region getById(int id) throws EntityNotFoundException{
    Region region=mapper.getById(id);
    if (region==null) throw new EntityNotFoundException(Region.class,"id", Integer.toString(id));
    log.info("getById");
    return region;
  }

  @Override
  public Region getByCode(String code) throws EntityNotFoundException {
    Region region = mapper.getByCod(code);
    if (region==null) throw new EntityNotFoundException(Region.class,"code", code);
    log.info("getByCod");
    return region;
  }
}
