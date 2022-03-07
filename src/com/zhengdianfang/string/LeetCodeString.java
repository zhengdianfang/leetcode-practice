package com.zhengdianfang.string;


import java.util.HashMap;
import java.util.Map;

public class LeetCodeString {
    public static void main(String[] args) {
        LeetCodeString leetCodeString = new LeetCodeString();
        int reverse = leetCodeString.reverse(211);
        System.out.println(leetCodeString.myAtoi("00000-42a1234"));
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

    /**
     * 反转字符串
     *
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *
     * @param s
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[left];
            s[left] = s[right];;
            s[right] = temp;
            ++left;
            --right;
        }
    }

    /**
     * 整数反转
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int rev = 0;
        while(x != 0) {
         if(rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
             return 0;
         }
         int digit = x % 10;
         x /= 10;
         rev = rev * 10 + digit;
        }
        return rev;
    }

    /**
     * 字符串中的第一个唯一字符
     * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        Map<Character, Integer> frequency = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); ++i) {
            if (frequency.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 有效的字母异位词
     *
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            characterIntegerHashMap.put(c, characterIntegerHashMap.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            characterIntegerHashMap.put(c, characterIntegerHashMap.getOrDefault(c, 0) - 1);
            if(characterIntegerHashMap.get(c) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
       if (s.length() == 0) {
           return false;
       }
       int left = 0;
       int right = s.length() - 1;
       s = s.toLowerCase();
       while (left < right) {
           while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
               left++;
           }
           while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
               right--;
           }
           if (s.charAt(left) != s.charAt(right)) {
               return false;
           }
           left++;
           right--;
       }
       return true;
    }

    /**
     * 请你来实现一个myAtoi(string s函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     *
     * 函数myAtoi(string s) 的算法如下：
     *
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231231− 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231− 1 的整数应该被固定为 231− 1 。
     * 返回整数作为最终结果。
     * 注意：
     *
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        int symbol = 1;
        double sum = 0;
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!flag && c == ' ') {
                continue;
            }
            if (!flag && c == '-') {
                symbol = -1;
                flag = true;
                continue;
            }
            if (!flag && c == '+') {
                symbol = 1;
                flag = true;
                continue;
            }
            if (c < '0' || c > '9') {
                break;
            }
            flag = true;
            int num = c - '0';
            sum = sum * 10 + num;
            if (sum > Integer.MAX_VALUE) {
                return symbol == 1 ? Integer.MAX_VALUE : -Integer.MAX_VALUE - 1;
            }
        }
        return (int) sum * symbol;
    }
}
