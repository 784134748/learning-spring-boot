package com.yalonglee.learning.account.mapper.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 
 */
public interface AccountBaseMapper<T> {

    /**
     * 新增
     *
     * @param t
     * @return
     */
    Integer insert(T t);

    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(@Param("id") Object id);

    /**
     * 通过条件删除
     *
     * @param t
     * @return
     */
    Integer deleteByQuery(@Param("t") T t);

    /**
     * 全量更新
     *
     * @param t
     * @return
     */
    Integer fullUpdate(T t);

    /**
     * 增量更新
     *
     * @param t
     * @return
     */
    Integer incUpdate(T t);

    /**
     * 通过主键查询
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(@Param("id") Object id);

    /**
     * 通过条件查询One
     *
     * @param t
     * @return
     */
    T selectOneByQuery(@Param("t") T t);

    /**
     * 通过条件查询
     *
     * @param t
     * @param start
     * @param end
     * @return
     */
    List<T> selectByQuery(@Param("t") T t, @Param("start") Integer start, @Param("end") Integer end);

    /**
     * 通过条件查询条数
     *
     * @param t
     * @return
     */
    Integer count(@Param("t") T t);

}
