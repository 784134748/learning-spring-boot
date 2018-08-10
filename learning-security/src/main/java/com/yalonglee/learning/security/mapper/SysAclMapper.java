package com.yalonglee.learning.security.mapper;

import com.yalonglee.learning.security.model.SysAcl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysAclMapper {

    SysAcl selectByPrimaryKey(@Param("id") Long id);

}