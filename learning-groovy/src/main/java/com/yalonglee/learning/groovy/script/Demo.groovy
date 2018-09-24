package com.yalonglee.learning.groovy.script

import groovy.sql.Sql

/**
 * 模板groovy
 */

/**
 * 初始化 函数
 * 执行器在加载的时候会调用
 */
def init() {
}

/**
 * 执行函数
 * 执行器执行入口
 * @param context 上下文
 */
def run(context) {
    def url = 'jdbc:mysql://106.14.15.55:3306/test1?useAffectedRows=true&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true'
    def user = 'root'
    def password = '123456'
    def driver = 'com.mysql.jdbc.Driver'
    def sql = Sql.newInstance(url, user, password, driver)
    sql.firstRow('SELECT "x"')
    sql.close()
}

/**
 * 执行完毕销毁函数
 */
def destroy() {

}

public static void main(def args){
    run("")
}

