package com.yalonglee.learning.security.mapper.base;

import com.yalonglee.learning.security.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserBaseMapper {

    SysUser selectByPrimaryKey(@Param("id") Long id);

    List<SysUser> selectByQuery(Map<String, Object> param);

    void deleteByPrimaryKey(@Param("id") Long id);

    Integer count(Map<String, Object> param);

    Long insert(Map<String, Object> param);

    void update(Map<String, Object> param);

}
