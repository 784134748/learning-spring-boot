package com.yalonglee.learning.security.mapper.base;

import com.yalonglee.learning.security.model.SysAcl;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysAclBaseMapper {

    SysAcl selectByPrimaryKey(@Param("id") Long id);

    List<SysAcl> selectByQuery(Map<String, Object> param);

    Integer deleteByPrimaryKey(@Param("id") Long id);

    Integer count(Map<String, Object> param);

    Long insert(Map<String, Object> param);

    Integer update(Map<String, Object> param);

}
