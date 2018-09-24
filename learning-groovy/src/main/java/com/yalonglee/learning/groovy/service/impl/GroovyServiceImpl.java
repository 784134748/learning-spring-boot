package com.yalonglee.learning.groovy.service.impl;

import com.yalonglee.learning.core.common.Result;
import com.yalonglee.learning.groovy.service.GroovyExecService;
import com.yalonglee.learning.groovy.service.GroovyService;
import com.yalonglee.learning.groovy.model.GroovyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.Invocable;
import java.util.Objects;

/**
 * @author shaoshuai
 * @since 2018-09-18 14:04
 */
@Service
public class GroovyServiceImpl implements GroovyService {

    @Autowired
    private GroovyExecService groovyExecService;

    @Override
    public Result execGroovySync(GroovyModel groovyModel) {
        if (Objects.isNull(groovyModel.getScript())) {
            return Result.fail("脚本内容不能为空");
        }
        Object objResult = groovyExecService.runGroovyScript(groovyModel);
        Result result = Result.success();
        result.setEntry(objResult);
        return result;
    }

    @Override
    public Result<Invocable> getGroovyInvocable(GroovyModel groovyModel) {
        if (Objects.isNull(groovyModel.getScript())) {
            return Result.fail("脚本内容不能为空");
        }
        Invocable inv = groovyExecService.getGroovyInvocable(groovyModel);
        Result result = Result.success();
        result.setEntry(inv);
        return result;
    }
}
