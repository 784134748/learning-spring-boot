package com.yalonglee.learning.core.utils;

import java.io.*;

public class String2File {

    private static final int DEFAULT_BUFFER_SIZE = 1024;

    /**
     * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！)
     *
     * @param res      原字符串
     * @param filePath 文件路径
     * @return 成功标记
     */
    public static boolean string2File(String res, String filePath) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter;
        try {
            File distFile = new File(filePath);
            if (!distFile.getParentFile().exists()) {
                distFile.getParentFile().mkdirs();
            }
            bufferedReader = new BufferedReader(new StringReader(res));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            //字符缓冲区
            char[] buf = new char[DEFAULT_BUFFER_SIZE];
            int len;
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

}
