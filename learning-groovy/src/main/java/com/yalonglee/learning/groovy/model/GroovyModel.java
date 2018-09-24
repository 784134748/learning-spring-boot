package com.yalonglee.learning.groovy.model;

/**
 * @author shaoshuai
 * @since 2018-09-18 15:13
 */
public class GroovyModel {

    /**
     * 脚本内容
     */
    private String script;

    /**
     * 超时 单位 秒
     */
    private Long execTimeout = 3L;
    /**
     * 上下文参数
     */
    private String context;

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Long getExecTimeout() {
        return execTimeout;
    }

    public void setExecTimeout(Long execTimeout) {
        this.execTimeout = execTimeout;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
