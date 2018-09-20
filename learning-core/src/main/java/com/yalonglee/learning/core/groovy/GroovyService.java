package com.yalonglee.learning.core.groovy;

import com.yalonglee.learning.core.common.Result;
import com.yalonglee.learning.core.groovy.Model.GroovyModel;
import org.springframework.stereotype.Service;

import javax.script.Invocable;

/**
 * @author shaoshuai
 * @since 2018-09-18 13:59
 */
@Service
public interface GroovyService {

    /**
     * 执行groovy脚本
     *
     * @param groovyModel groovy对象
     * @return
     */
    Result execGroovySync(GroovyModel groovyModel);


    /**
     * 获取函数执行器
     * @param groovyModel groovy 对象
     * @return
     */
    Result<Invocable> getGroovyInvocable(GroovyModel groovyModel);

}
