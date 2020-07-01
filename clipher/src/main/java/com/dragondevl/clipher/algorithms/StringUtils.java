package com.dragondevl.clipher.algorithms;


public class StringUtils {

    public static final String DEFAULT_CHARS = "0123456789ABCDEF";

    public static String bytes2HexStr(byte[] request) {
        if (request == null) {
            return null;
        }
        char[] chars = DEFAULT_CHARS.toCharArray();
        StringBuilder sb = new StringBuilder();
        int bit;
        for (byte b : request) {
            bit = (b & 0xf0) >> 4;
            sb.append(chars[bit]);
            bit = b & 0xf0;
            sb.append(chars[bit]);
        }
        return sb.toString();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isSpace(String str) {
        return !isEmpty(str) && str.trim().length() == 0;
    }

}
