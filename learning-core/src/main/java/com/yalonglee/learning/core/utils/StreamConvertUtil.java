package com.yalonglee.learning.core.utils;

import java.io.*;

public class StreamConvertUtil {

    /**
     * inputStream转outputStream
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static ByteArrayOutputStream parse(final InputStream in) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int ch;
        while ((ch = in.read()) != -1) {
            byteArrayOutputStream.write(ch);
        }
        return byteArrayOutputStream;
    }

    /**
     * outputStream转inputStream
     *
     * @param out
     * @return
     */
    public static ByteArrayInputStream parse(final OutputStream out) {
        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) out;
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return byteArrayInputStream;
    }

    /**
     * inputStream转String
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static String parse_String(final InputStream in) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int ch;
        while ((ch = in.read()) != -1) {
            byteArrayOutputStream.write(ch);
        }
        return byteArrayOutputStream.toString();
    }

    /**
     * OutputStream转String
     *
     * @param out
     * @return
     */
    public static String parse_String(final OutputStream out) {
        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) out;
        final ByteArrayInputStream swapStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return swapStream.toString();
    }

    /**
     * String转inputStream
     *
     * @param in
     * @return
     */
    public static ByteArrayInputStream parse_inputStream(final String in) {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(in.getBytes());
        return byteArrayInputStream;
    }

    /**
     * String转outputStream
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static ByteArrayOutputStream parse_outputStream(final String in) throws Exception {
        return parse(parse_inputStream(in));
    }

}
