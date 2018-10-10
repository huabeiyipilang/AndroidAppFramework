package com.penghaonan.appframework.utils;

public class StringUtils {
    public static boolean equals(String s1, String s2) {
        if (s1 == null) {
            return s2 == null;
        }else {
            return s1.equals(s2);
        }
    }
}
