package com.cysds;

/**
 * @author: 谢玮杰
 * @description:
 * @create: 2025-05-20 22:33
 **/
public class test {

    public static void main(String[] args) {
        String s = "abc";
        String a = new String("abc").intern();
        System.out.println(s == a);
    }
}
