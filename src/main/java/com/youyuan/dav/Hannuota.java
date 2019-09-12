package com.youyuan.dav;

/**
 * @author zhangyu
 * @version 1.0
 * @description 分治算法应用场景  汉诺塔
 * <p>
 * 1、n：代表几个盘子   A：A塔            B：B塔         C：C塔
 * 2、当n=1时       A->C
 * 3、当n>1时 ，把n看成两个盘子最底下的盘子n 和 除了底下的盘子(n-1)个盘子
 * n-1看成一个盘子     A->B
 * 第n盘子      A->C
 * n-1在分解按照上面步骤，最后将n-1的盘子  B->C
 * @date 2019/8/21 22:13
 */
public class Hannuota {

    public static void main(String[] args) {
        int num = 5;
        char a = 'A';
        char b = 'B';
        char c = 'C';
        hannuo(num, a, b, c);
    }

    /**
     * 分治算法应用场景汉诺塔移动
     *
     * @param num 第几个盘子
     * @param a   A塔
     * @param b   B塔
     * @param c
     */
    public static void hannuo(int num, char a, char b, char c) {
        if (num == 1) {  //只有一个盘子的时候
            System.out.println("第" + num + "个盘子从" + a + "->" + c);
        } else {
            //将mum分成两部分  第num和第num-1(看成整体)
            //1.先把 最上面的所有盘 A->B， 移动过程会使用到 c
            hannuo(num - 1, a, c, b);  //从A->B  借助C
            //2.最下面的盘子从A->C
            System.out.println("第" + num + "个盘子从" + a + "->" + c);
            //3.把 B盘移动到C盘
            hannuo(num - 1, b, a, c);  // B->C  借助A
        }
    }

}
