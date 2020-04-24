package com.jinyu;

/**
 * @author <a href="jinyu52370@163.com">JJJ</a>
 * @date 2020/4/23 17:53
 */
public class KMP {
    public static int violenceMatch(String sourceStr, String targetStr) {
        if (targetStr.length() > sourceStr.length()){
            System.out.println("待匹配字符串不能大于源字符串");
            return -1;
        }

        char[] source = sourceStr.toCharArray();
        char[] target = targetStr.toCharArray();

        int i = 0, j = 0;
        while (i < source.length && j < target.length) {
            if (source[i] == target[j]) {
                i++;
                j++;
                continue;
            }
            i = i - j + 1;
            j = 0;
        }

        if (j == target.length){
            return i - j;
        }
        return -1;
    }

    public static int kmp(String sourceStr, String targetStr){
        int[] next = kmpNext(targetStr);
        for (int i = 0, j = 0; i < sourceStr.length(); i++) {
            while (j > 0 && sourceStr.charAt(i) != targetStr.charAt(j)){
                j = next[j - 1];
            }

            if (sourceStr.charAt(i) == targetStr.charAt(j)){
                j++;
            }
            if (j == targetStr.length()){
                return i - j + 1;
            }
        }
        return -1;
    }

    private static int[] kmpNext(String targetStr){
        if (targetStr == null || "".equals(targetStr)){
            return null;
        }
        int[] next = new int[targetStr.length()];
        next[0] = 0;

        for (int i = 1, j = 0; i < next.length; i++) {
            while (j > 0 && targetStr.charAt(i) != targetStr.charAt(j)){
                j = next[j - 1];
            }
            if (targetStr.charAt(i) == targetStr.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}

