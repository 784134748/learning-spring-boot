package com.yalonglee.learning.groovy;

import com.yalonglee.learning.core.utils.File2String;
import com.yalonglee.learning.groovy.service.GroovyExecService;
import com.yalonglee.learning.groovy.model.GroovyModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * @author shaoshuai
 * @since 2018-09-18 14:34
 */
public class GroovyExecServiceTest extends LearningGroovyApplicationTests {

    @Autowired
    private GroovyExecService groovyExecService;

    @Test
    public void textRunGroovy1() {
        File file = new File("/Users/yalonglee/Documents/GitHub/learning-spring-boot/learning-groovy/src/main/java/com/yalonglee/learning/groovy/script/Demo.groovy");
        String script = File2String.file2String(file, "utf-8");
        GroovyModel groovyModel = new GroovyModel();
        groovyModel.setScript(script);
        groovyModel.setContext("这是参数");

        Object res = groovyExecService.runGroovyScript(groovyModel);
        System.out.print(res);
    }

}
