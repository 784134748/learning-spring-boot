package com.yalonglee.learning.core.contrallor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>《一句话功能简述》
 * <p><功能详细描述>
 * <p>
 * <p>Copyright (c) 2018, listener@iflytek.com All Rights Reserve</p>
 * <p>Company : 科大讯飞</p>
 *
 * @author listener
 * @version [V1.0, 2018/7/11]
 * // * @see [相关类/方法]
 */
@RestController
public class StatusController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home() {
        return "api server!";
    }

}
