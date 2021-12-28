package com.zhengdianfang.string;


import java.util.HashMap;
import java.util.HashSet;

public class LeetCodeString {
    public static void main(String[] args) {
        LeetCodeString leetCodeString = new LeetCodeString();
        System.out.println(leetCodeString.getMaxLengthForNoRepeatWords("abcabcbb"));
        System.out.println(leetCodeString.getMaxLengthForNoRepeatWords("pwwkew"));
    }

    public int getMaxLengthForNoRepeatWords(String s) {
        HashMap<Character, Integer> cache = new HashMap();
        int start = 0;
        int maxNoRepeatLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cache.containsKey(c)) {
               start = Math.max(start, cache.get(c));
            }
            maxNoRepeatLen = Math.max(maxNoRepeatLen, i - start + 1);
            cache.put(c, i + 1);
        }

        return maxNoRepeatLen;
    }
}
