package com.yalonglee.learning.security.mapper;

import com.yalonglee.learning.security.model.SysAcl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysAclMapper {

  SysAcl selectByPrimaryKey(@Param("id") Long id);

  List<SysAcl> selectByQuery(Map<String, Object> param);

  void deleteByPrimaryKey(@Param("id") Long id);

  Integer count(Map<String, Object> param);

  Long insert(Map<String, Object> param);

  void update(Map<String, Object> param);

}
