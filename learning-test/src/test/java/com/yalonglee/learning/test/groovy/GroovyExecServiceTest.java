package com.yalonglee.learning.test.groovy;

import com.yalonglee.learning.core.groovy.GroovyExecService;
import com.yalonglee.learning.core.groovy.Model.GroovyModel;
import com.yalonglee.learning.test.LearningTestApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shaoshuai
 * @since 2018-09-18 14:34
 */
public class GroovyExecServiceTest extends LearningTestApplicationTests {

    @Autowired
    private GroovyExecService groovyExecService;

    @Test
    public void textRunGroovy1() {
//        String script = "def hello(param1,param2) {return \"the params is $param1 and $param2\"}";
        String script = "/**\n" +
                " * 初始化 函数\n" +
                " * 执行器在加载的时候会调用\n" +
                " */\n" +
                "def init() {\n" +
                "\nprintln(\"init...\")" +
                "}\n" +
                "\n" +
                "/**\n" +
                " * 执行函数\n" +
                " * 执行器执行入口\n" +
                " * @param context 上下文\n" +
                " */\n" +
                "def run(context) {\n" +
                "\nprintln(\"run...\")" +
                "\nreturn \"哈哈哈哈这是run函数\"" +
                "}\n" +
                "\n" +
                "/**\n" +
                " * 执行完毕销毁函数\n" +
                " */\n" +
                "def destroy() {\n" +
                "\nprintln(\"destroy...\")" +
                "}";
        GroovyModel groovyModel = new GroovyModel();
        groovyModel.setScript(script);
        groovyModel.setContext("这是参数");

        Object res1 = groovyExecService.runGroovyScript(groovyModel);
    }

}
