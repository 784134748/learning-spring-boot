package com.yalonglee.learning.groovy.service;

import com.yalonglee.learning.groovy.model.GroovyModel;

import javax.script.Invocable;
import java.util.Map;

/**
 * @author shaoshuai
 * @since 2018-09-18 14:19
 */
public interface GroovyExecService {


    /**
     * 执行groovy脚本(不指定方法)
     *
     * @param script 要执行的脚本 通过字符串传入，应用场景 如从数据库中读取出来的脚本等
     * @param params 执行grovvy需要传入的参数
     * @return 脚本执行结果
     */
    Object runGroovyScript(String script, Map<String, Object> params);


    /**
     * 执行groovy脚本(需要指定方法名)
     *
     * @param script  要执行的脚本 通过字符串传入，应用场景 如从数据库中读取出来的脚本等
     * @param funName 要执行的方法名
     * @param params  执行grovvy需要传入的参数
     * @return
     */
    Object runGroovyScript(String script, String funName, Object[] params);


    /**
     * 执行groovyModel对象
     *
     * @param groovyModel groovy对象
     * @return
     */
    Object runGroovyScript(GroovyModel groovyModel);


    /**
     * 获取函数调用
     *
     * @param groovyModel groovyModel
     * @return
     */
    Invocable getGroovyInvocable(GroovyModel groovyModel);

}
