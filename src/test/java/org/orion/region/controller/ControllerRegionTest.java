package org.orion.region.controller;


import java.util.List;
import java.util.Objects;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.orion.region.RegionApplicationTests;
import org.orion.region.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ControllerRegionTest {

  public static final Region region1 = new Region(1, "Республика Адыгея (Адыгея)", "01");
  public static final Region region2 = new Region(2, "Республика Башкортостан", "02");
  public static final Region region4 = new Region(10000, "Астрахань", "30");
  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void getAll() {
    ResponseEntity<List<Region>> response = restTemplate.exchange("/enter", HttpMethod.GET, null,
        new ParameterizedTypeReference<List<Region>>() {
        });
    List<Region> regions = response.getBody();
    assert regions != null;
    Assert.assertEquals(regions.size(),8);
    Assert.assertEquals(regions.get(4).getCode(),"03");
  }

  @Test
  void getById() {
    Region region = restTemplate.getForObject("/enter/{id}", Region.class, 1);
    Assert.assertEquals(region, region1);
  }

  @Test
  void getByCode() {
    Region region = restTemplate.getForObject("/enter/code/{code}", Region.class, "02");
    Assert.assertEquals(region, region2);
  }

  @Test
  void update() {
    Region region = new Region("new", "new");
    HttpEntity<Region> entity = new HttpEntity<>(region);
    ResponseEntity<Region> response = restTemplate
        .exchange("/enter/{id}", HttpMethod.PUT, entity, Region.class,
            2);
    Assert.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);

  }

  @Test
  void createRegion() {
    Region r = new Region("asdffdasdf", "asdf");
    ResponseEntity<Region> response = restTemplate.postForEntity("/enter", r, Region.class);
    Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    Assert.assertEquals(Objects.requireNonNull(response.getBody()).getCode(), "asdf");
    Assert.assertEquals(response.getBody().getName(), "asdffdasdf");


  }

  @Test
  void deleteById() {

  }
}