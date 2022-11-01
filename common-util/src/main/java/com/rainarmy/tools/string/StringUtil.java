package com.rainarmy.tools.string;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class StringUtil {
    public static String removeXandAfter(String inString, String pattern) {
        return inString.replaceAll(pattern + ".*", "");
    }

    public static String removeXYandBetween(String inString, String patternX, String patternY) {
        return inString.replaceAll(patternX + ".*" + patternY, "");
    }

    public static String remove(String str, String remove) {
        return StringUtils.remove(str, remove);
    }

    public static String removeAny(String str, String[] removes) {
        for (String remove : removes) {
            str = remove(str, remove);
        }
        return str;
    }

    public static String substringBetweenAny(String inString, String patternX, String[] patternY) {
        for (int i = 0; i < patternY.length; i++) {
            String str = substringBetween(inString, patternX, patternY[i]);
            if (str != null) {
                return str;
            }
        }
        return "";
    }

    public static String substringBetweenAny(String inString, String patternX[], String patternY) {
        for (int i = 0; i < patternX.length; i++) {
            String str = substringBetween(inString, patternX[i], patternY);
            if (str != null) {
                return str;
            }
        }
        return "";
    }

    public static String substringBetweenAny(String inString, String[] patternX, String[] patternY) {
        if (patternX.length != patternY.length) {
            throw new IllegalArgumentException("substringBetweenAny, patternX.length != patternY.length");
        }
        for (int i = 0; i < patternX.length; i++) {
            String str = substringBetween(inString, patternX[i], patternY[i]);
            if (str != null) {
                return str;
            }
        }
        return "";
    }

    public static String substringBetween(String inString, String patternX, String patternY) {
        int s = inString.indexOf(patternX);
        if (s != -1) {
            int e = inString.indexOf(patternY);
            if (e != -1) {
                return inString.substring(s + patternX.length(), e);
            }
        }
        return null;
    }

    public static boolean isInteger(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static boolean isDouble(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?\\d*[.]\\d+$"); // 之前这里正则表达式错误，现更正
        return pattern.matcher(str).matches();
    }
}
