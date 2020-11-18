package org.orion.region.service;


import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.orion.region.RegionApplicationTests;
import org.orion.region.error.EntityNotFoundException;
import org.orion.region.model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class RegionServiceImplTest {
Logger log = LoggerFactory.getLogger(RegionServiceImplTest.class);
  @Autowired
  private RegionService service;

  public static final Region region1 = new Region(1, "Республика Адыгея (Адыгея)", "01");
  public static final Region region2 = new Region(2, "Республика Башкортостан", "02");
  public static final Region region4 = new Region(10000, "Астрахань", "30");


  @Test
  void create() {
    Region region = service.create(new Region("Астрахань", "30"));
    Assert.assertEquals(region, region4);
  }


  @Test
  void getById() throws EntityNotFoundException {
    Region region = service.getById(1);
    Assert.assertEquals(region, region1);
  }

  @Test
  void getByCode() throws EntityNotFoundException {
    Region region = service.getByCode("01");
    Assert.assertEquals(region, region1);
    Assert.assertNotEquals(region, region2);

  }

  @Test
  void update() throws EntityNotFoundException {
    Region regionGet = service.getById(8);
    regionGet.setName("test");
    regionGet.setCode("50");
    Region region = service.update(new Region(8, "test", "50"));
    Assert.assertEquals(regionGet, region);

  }

  @Test
  void delete() throws EntityNotFoundException {
    service.getById(5);
    service.delete(5);
    Assert.assertThrows(EntityNotFoundException.class, () -> service.getById(5));
  }

  @Test
  void notFoundById() throws EntityNotFoundException {

    Assert.assertThrows(EntityNotFoundException.class, () -> service.getById(20));
  }

  @Test
  void notFoundByCode() throws EntityNotFoundException {

    Assert.assertThrows(EntityNotFoundException.class, () -> service.getByCode("38"));
  }

  @Test
  void getAll() {
  }

  @Test
  void getByPattern() {
    List<Region> regions = service.getByPattern("%Респ%");
    regions.forEach(a -> log.info(a.toString()));
  }
}