package com.yalonglee.learning.core.common;

/**
 * @author yalonglee
 */
public class BaseVO {

    public String getVOName() {
        String classname = new Exception().getStackTrace()[1].getClassName();
        return classname;
    }

}
