package com.youyuan.kmp;

/**
 * @author zhangyu
 * @version 1.0
 * @description 暴力匹配算法 场景 匹配字符串
 * <p>
 * 思路：
 * 1.如果当前字符匹配成功（即str1[i] == str2[j]），则i++，j++，继续匹配下一个字符
 * 2.如果失配（即str1[i]! = str2[j]），令i = i - (j - 1)，j = 0。相当于每次匹配失败时，i 回溯，j 被置为0。   i=i-j+1;  例如i=5   j=2  则i从上次匹配位置的下一个位置在开始匹配并且把j=0
 * 3.用暴力方法解决的话就会有大量的回溯，每次只移动一位，若是不匹配，移动到下一位接着判断，浪费了大量的时间。(不可行!)
 * @date 2019/8/22 19:21
 */
public class ViolentMath {

    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = math(str1, str2);
        System.out.println("匹配的位置:" + index);
        System.out.println(str1.indexOf(str2));
    }

    /**
     * 暴力匹配算法
     *
     * @param str1 原始字符串
     * @param str2 要匹配的字符串
     * @return 如果匹配到返回要匹配字符串在原始字符串的下标，否则返回-1
     */
    public static int math(String str1, String str2) {
        char[] chars1 = str1.toCharArray();  //原始字符串转成字符数组
        char[] chars2 = str2.toCharArray();  //要匹配字符串转成字符数组
        int s1Len = chars1.length;   //原始字符串字符数组长度
        int s2Len = chars2.length;   //要匹配字符串字符数组长度
        int i = 0;  //原始字符数组下标
        int j = 0;  //要匹配字符数组下标

        //防止下标越界
        while (i < s1Len && j < s2Len) {
            if (chars1[i] == chars2[j]) {//字符匹配上
                i++;
                j++;
            } else {  //没有匹配上 需要将i置为上次匹配起始下标的下一个下标  j置为0从头开始匹配
                i = i - j + 1;  //i指向上次匹配的下一个下标
                j = 0;
            }
        }

        if (j == s2Len) {  //代表j下标已经走了一遍，全部匹配上
            return i - j;  //开始匹配的位置
        } else {
            return -1;
        }
    }

}
