package org.orion.region.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.orion.region.model.Region;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface MapperRegion {

  @Select("SELECT * FROM regions  ORDER BY name ")
  @Results(value = {
      @Result(property = "id", column = "id"),
      @Result(property = "name", column = "name"),
      @Result(property = "code", column = "code")
  })
  List<Region> getAll();

  @Select("SELECT * FROM regions WHERE code = #{code}")
  Region getByCod(@Param(value = "code") String code);

  @Select("SELECT * FROM regions WHERE id = #{id}")
  Region getById(@Param(value = "id") int id);

  @Update("UPDATE regions SET id=#{id}, name=#{name}, code=#{code}  WHERE id=#{id}")
  int updateRegion(Region region);

  @Insert("INSERT into regions (name,code) values ( #{name},#{code}) ")
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  int create(Region region);

  @Delete("DELETE FROM regions WHERE id=#{id}")
  boolean deleteRegion(@Param(value = "id") int id);

  @Select("SELECT * FROM regions WHERE name LIKE #{pattern}")
  List<Region> getByPattern(@Param(value = "pattern") String pattern);


}
