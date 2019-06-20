package com.yalonglee.learning.groovy.script
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
//连接数据库
//    def url = 'jdbc:mysql://106.14.15.55:3306/test1?useAffectedRows=true&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true'
//    def user = 'root'
//    def password = '123456'
//    def driver = 'com.mysql.jdbc.Driver'
//    def sql = Sql.newInstance(url, user, password, driver)
//    sql.firstRow('SELECT "x"')
//    sql.close()

    def fields = [1, 2]

    def out

    replace(out, "/Users/yalonglee/Desktop/src/main/resources/mapper/CategoryMapper.xml", fields)
}

def replace(out, path, fields) {
    def ignore = false
    def time = 0
    list = []
    BufferedReader reader = new BufferedReader(new FileReader(new File(path)))
    try {
        String line
        while ((line = reader.readLine()) != null) {
            if (ignore == false) {
                out.print(line)
            }
            if (line.contains("<!--华丽的分割线-->")) {
                ignore = !ignore
                time += 1
                fields.each() {

                }
            }
            if (time == 2) {
                out.print(line)
                time += 1
            }
        }
    } finally {
        reader.close()
    }
    System.out.println(out)
}

/**
 * 执行完毕销毁函数
 */
def destroy() {

}

public static void main(def args) {
    run("")
}

