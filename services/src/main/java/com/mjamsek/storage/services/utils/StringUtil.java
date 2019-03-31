package com.mjamsek.storage.services.utils;

import java.security.SecureRandom;
import java.util.Arrays;

public class StringUtil {
    
    public static String joinStrings(Iterable<String> strings, String delimitor) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirst = true;
        for (String item : strings) {
            if (!isFirst) {
                stringBuilder.append(delimitor);
            }
            stringBuilder.append(item);
            isFirst = false;
        }
        return stringBuilder.toString();
    }
    
    public static String joinStrings(String[] strings, String delimitor) {
        return joinStrings(Arrays.asList(strings), delimitor);
    }
    
    public static String generateRandomString(int len) {
        String symbols = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(symbols.charAt(random.nextInt(symbols.length())));
        }
        return sb.toString();
    }
    
}
