package com.yalonglee.learning.core.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Param2 {

    private static Logger logger = LoggerFactory.getLogger(Param2.class);

    /**
     * 将javaBean转换为新增参数
     * 非空转换
     * @param o
     * @return
     */
    public static Map<String, Object> getInsertParams(Object o) {
        Map<String, Object> p = Maps.newHashMap();
        //获取关联的所有类，本类以及所有父类
        boolean ret = true;
        Class oo = o.getClass();
        List<Class> clazzs = new ArrayList<Class>();
        while (ret) {
            clazzs.add(oo);
            oo = oo.getSuperclass();
            if (oo == null || oo == Object.class) {
                break;
            }
        }

        Object value;
        for (int i = 0; i < clazzs.size(); i++) {
            Field[] declaredFields = clazzs.get(i).getDeclaredFields();
            for (Field field : declaredFields) {
                int mod = field.getModifiers();
                //过滤 static 和 final 类型
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                try {

                    value = field.get(o);
                    if (value == null) {
                        continue;
                    }

                    p.put(field.getName(), value);
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage());
                }
            }
        }

        return p;
    }

    /**
     * 将javaBean转换为更新参数
     * 全转换
     * @param o
     * @return
     */
    public static Map<String, Object> getUpdateParams(Object o) {
        Map<String, Object> p = Maps.newHashMap();
        //获取关联的所有类，本类以及所有父类
        boolean ret = true;
        Class oo = o.getClass();
        List<Class> clazzs = new ArrayList<Class>();
        while (ret) {
            clazzs.add(oo);
            oo = oo.getSuperclass();
            if (oo == null || oo == Object.class) {
                break;
            }
        }

        for (int i = 0; i < clazzs.size(); i++) {
            Field[] declaredFields = clazzs.get(i).getDeclaredFields();
            for (Field field : declaredFields) {
                int mod = field.getModifiers();
                //过滤 static 和 final 类型
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                try {
                    p.put(field.getName(), field.get(o));
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage());
                }
            }
        }

        return p;
    }

    /**
     * 将javaBean转换为查询参数
     * 非空白转换
     * @param o
     * @return
     */
    public static Map<String, Object> getQueryParams(Object o) {
        Map<String, Object> p = Maps.newHashMap();
        //获取关联的所有类，本类以及所有父类
        boolean ret = true;
        Class oo = o.getClass();
        List<Class> clazzs = new ArrayList<Class>();
        while (ret) {
            clazzs.add(oo);
            oo = oo.getSuperclass();
            if (oo == null || oo == Object.class) {
                break;
            }
        }

        Object value;
        for (int i = 0; i < clazzs.size(); i++) {
            Field[] declaredFields = clazzs.get(i).getDeclaredFields();
            for (Field field : declaredFields) {
                int mod = field.getModifiers();
                //过滤 static 和 final 类型
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                try {

                    value = field.get(o);
                    if (value == null) {
                        continue;
                    }

                    if (value instanceof String) {
                        if(value == null){
                            continue;
                        }
                        if("%null%".equals(value)){
                            continue;
                        }
                        if("%null".equals(value)){
                            continue;
                        }
                        if("null%".equals(value)){
                            continue;
                        }
                        if (StringUtils.isBlank(value.toString())) {
                            continue;
                        }
                    }
                    p.put(field.getName(), value);
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage());
                }
            }
        }

        return p;
    }
}
