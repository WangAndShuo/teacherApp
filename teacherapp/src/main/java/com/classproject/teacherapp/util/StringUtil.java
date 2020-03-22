package com.classproject.teacherapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * describe: StringUtil 工具类
 * date 2019/2/26
 */

public class StringUtil {
    public StringUtil() {
    }

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else {
            return str.trim().equals("");
        }
    }

    public static String trimPrefix(String toTrim, String trimStr) {
        while (toTrim.startsWith(trimStr)) {
            toTrim = toTrim.substring(trimStr.length());
        }

        return toTrim;
    }

    public static String trimSufffix(String toTrim, String trimStr) {
        while (toTrim.endsWith(trimStr)) {
            toTrim = toTrim.substring(0, toTrim.length() - trimStr.length());
        }

        return toTrim;
    }

    public static String trim(String toTrim, String trimStr) {
        return trimSufffix(trimPrefix(toTrim, trimStr), trimStr);
    }

    public static String replace(String content, String startTag, String endTag, String repalceWith) {
        String tmp = content.toLowerCase();
        String tmpStartTag = startTag.toLowerCase();
        String tmpEndTag = endTag.toLowerCase();
        StringBuilder sb = new StringBuilder();
        int beginIndex = tmp.indexOf(tmpStartTag);
        int endIndex = tmp.indexOf(tmpEndTag);

        while (beginIndex != -1 && endIndex != -1 && beginIndex < endIndex) {
            String pre = content.substring(0, tmp.indexOf(tmpStartTag) + tmpStartTag.length());
            String suffix = content.substring(tmp.indexOf(tmpEndTag));
            tmp = suffix.toLowerCase();
            content = suffix.substring(endTag.length());
            beginIndex = tmp.indexOf(tmpStartTag);
            endIndex = tmp.indexOf(tmpEndTag);
            String replaced = pre + "\r\n" + repalceWith + "\r\n" + endTag;
            sb.append(replaced);
        }

        sb.append(content);
        return sb.toString();
    }

    public static String firstLetterToUpper(String str) {
        Pattern regex = Pattern.compile("^[A-Z].*?");
        Matcher regexMatcher = regex.matcher(str);
        if (regexMatcher.matches()) {
            return str;
        } else {
            char[] array = str.toCharArray();
            array[0] = (char) (array[0] - 32);
            return String.valueOf(array);
        }
    }

}

