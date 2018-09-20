package com.yalonglee.learning.core.groovy.impl;

import com.yalonglee.learning.core.groovy.GroovyExecService;
import com.yalonglee.learning.core.groovy.Model.GroovyModel;
import org.springframework.stereotype.Service;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Map;
import java.util.Objects;

/**
 * @author shaoshuai
 * @since 2018-09-18 14:25
 */
@Service
public class GroovyExecServiceImpl implements GroovyExecService {

    @Override
    public Object runGroovyScript(String script, Map<String, Object> params) {
        if (Objects.isNull(script) || Objects.equals(script, "")) {
//            throw new BizzRuntimeException("方法runGroovyScript无法执行，传入的脚本为空");
        }
        try {
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("groovy");
            Bindings bindings = engine.createBindings();
            bindings.putAll(params);
            return engine.eval(script, bindings);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object runGroovyScript(String script, String funName, Object[] params) {
        if (Objects.isNull(script) || Objects.equals(script, "")) {
//            throw new BizzRuntimeException("方法runGroovyScript无法执行，传入的脚本为空");
        }
        if (Objects.isNull(funName) || Objects.equals(funName, "")) {
//            throw new BizzRuntimeException("方法runGroovyScript无法执行，转入的函数为空");
        }
        try {
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("groovy");
            engine.eval(script);
            Invocable inv = (Invocable) engine;
            return inv.invokeFunction(funName, params);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object runGroovyScript(GroovyModel groovyModel) {
        if (Objects.isNull(groovyModel.getScript()) || Objects.equals(groovyModel.getScript(), "")) {
//            throw new BizzRuntimeException("方法runGroovyScript无法执行，传入的脚本为空");
        }
        try {
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("groovy");
            engine.eval(groovyModel.getScript());
            Invocable inv = (Invocable) engine;
            Object objInit = inv.invokeFunction("init");
            Object objRun = inv.invokeFunction("run", groovyModel.getContext());
            Object objDestroy = inv.invokeFunction("destroy");
            return objRun;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Invocable getGroovyInvocable(GroovyModel groovyModel) {
        if (Objects.isNull(groovyModel.getScript()) || Objects.equals(groovyModel.getScript(), "")) {
//            throw new BizzRuntimeException("方法runGroovyScript无法执行，传入的脚本为空");
        }
        try {
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("groovy");
            engine.eval(groovyModel.getScript());
            Invocable inv = (Invocable) engine;
            return inv;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
