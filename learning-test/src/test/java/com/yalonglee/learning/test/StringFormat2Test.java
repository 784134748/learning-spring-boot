package com.yalonglee.learning.test;

import com.yalonglee.learning.core.utils.StringFormat2;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class StringFormat2Test {

    // Map类型使用的格式化字符串
    private final static String formatForMap = "{name}, {china.name}, {usa.name}, {china.beijing.name}, {china.beijing.xicheng.name}, {world.region1}, {world.child.region1}.";
    // 类对象使用的格式化字符串
    private final static String formatForBean = "{region1}, {region2}, {child.region1}, {child.region2}, {child.child.region1}, {child.child.region2}, {date}, {child.date}.";

    private Map<String, Object> paramMap = null;
    private StringFormatTestBean paramBean = null;

    @Before
    public void testPrepare() {
        // paramBean初始化
        paramBean = new StringFormatTestBean();
        paramBean.setRegion1("世界");
        paramBean.setRegion2(null);
        StringFormatTestBean subBean = new StringFormatTestBean();
        subBean.setRegion1("中国");
        subBean.setRegion2("美国");
        paramBean.setChild(subBean);
        StringFormatTestBean subSubBean = new StringFormatTestBean();
        subSubBean.setRegion1("北京");
        subSubBean.setRegion2("河北");
        subBean.setChild(subSubBean);

        // paramMap初始化
        paramMap = new HashMap<>();
        paramMap.put("name", "世界");
        Map<String, Object> subParam = new HashMap<>();
        subParam.put("name", "中国");
        paramMap.put("china", subParam);
        Map<String, Object> subParam2 = new HashMap<>();
        subParam2.put("name", "美国");
        paramMap.put("usa", subParam2);
        Map<String, Object> subSubParam = new HashMap<>();
        subSubParam.put("name", "北京");
        subParam.put("beijing", subSubParam);
        Map<String, Object> subSubSubParam = new HashMap<>();
        subSubSubParam.put("name", "西城");
        subSubParam.put("xicheng", subSubSubParam);

        //注意：Map与Bean可以混用
        paramMap.put("world", subBean);
    }

    /**
     * 使用Map类型作为传入参数
     */
    @Test
    public void testFormatString1() {
        String result = StringFormat2.format(formatForMap, paramMap);
        System.out.print("TEST-101 => ");
        System.out.println(result);
    }

    /**
     * 使用普通Bean作为传入参数
     */
    @Test
    public void testFormatString2() {
        String result = StringFormat2.format(formatForBean, paramBean);
        System.out.print("TEST-201 => ");
        System.out.println(result);
    }

    /**
     * 几种可选的NULL值处理方式
     */
    @Test
    public void testFormatString3() {
        // NULL值不做替换
        String result = StringFormat2.format(formatForBean, paramBean, StringFormat2.PostHandleType.NULL_IGNOREANCE);
        System.out.print("TEST-301 => ");
        System.out.println(result);

        // NULL值替换为空字符串
        result = StringFormat2.format(formatForBean, paramBean, StringFormat2.PostHandleType.NULL_AS_EMPTY);
        System.out.print("TEST-302 => ");
        System.out.println(result);

        // NULL值替换为字符串“NULL”
        result = StringFormat2.format(formatForBean, paramBean, StringFormat2.PostHandleType.NULL_AS_STRING);
        System.out.print("TEST-303 => ");
        System.out.println(result);
    }

    /**
     * 高级用法 => 自定义数据处理接口，按你需要的格式输出
     */
    @Test
    public void testFormatString4() {

        // 放在匿名类外是为了演示需要向匿名类传值的使用场景
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // 自定义处理类
        // private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringFormat2.PostHandleInterface postHandler = (containerObject, name, fullVariableName, value) -> {
            /**
             * 注意： 1）各个逻辑的处理顺序，否则很容易出错； 2）value==null时调用toString()方法会抛空指针错误
             */
            String returnString = null;
            if ("region2".equals(name)) {
                returnString = String.format("region2: %s", value);
            } else if ("date".equals(name) && "date".equals(fullVariableName) && value != null
                    && value instanceof Date) {
                returnString = sdf.format(value);
            } else if (value == null) {
                returnString = null;
            } else {
                returnString = value.toString();
            }
            return returnString;
        };
        String result = StringFormat2.format(formatForBean, paramBean, postHandler);
        System.out.print("TEST-401 => ");
        System.out.println(result);
    }

    public class StringFormatTestBean {
        private String region1;
        private String region2;
        private Date date = new Date();
        private StringFormatTestBean child;

        public String getRegion1() {
            return region1;
        }

        public void setRegion1(String region1) {
            this.region1 = region1;
        }

        public String getRegion2() {
            return region2;
        }

        public void setRegion2(String region2) {
            this.region2 = region2;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public StringFormatTestBean getChild() {
            return child;
        }

        public void setChild(StringFormatTestBean child) {
            this.child = child;
        }
    }
}
