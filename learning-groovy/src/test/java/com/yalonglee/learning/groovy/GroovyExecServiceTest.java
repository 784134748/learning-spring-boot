package com.yalonglee.learning.groovy;

import com.yalonglee.learning.core.utils.File2String;
import com.yalonglee.learning.groovy.model.GroovyModel;
import com.yalonglee.learning.groovy.service.GroovyExecService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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

    @Test
    public void replace() throws IOException {

        boolean ignore = false;
        int index = 0;

        List<String> list = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/yalonglee/Desktop/src/main/resources/mapper/CategoryMapper.xml")));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                if (ignore == false) {
                    list.add(line);
                }
                if (line.contains("<!--华丽的分割线-->")) {
                    ignore = !ignore;
                    index += 1;
                }
                if (index == 2) {
                    list.add(line);
                    index += 1;
                }
            }
        } finally {
            reader.close();
        }

        for (String line : list) {
            System.out.println(line);
        }
    }

}
