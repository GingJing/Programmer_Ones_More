package com.mingyu.jvm;

/**
 * 测试1
 *
 * @date: 2020/9/10 8:39
 * @author: GingJingDM
 * @version: 1.0
 */
public class Test1 {

    public int add() {
        int a = 1;
        int b = 2;
        int c = a + b;
        return c;
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        int result = test1.add();
        System.out.println(test1);
    }
}
