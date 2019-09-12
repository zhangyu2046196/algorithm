package com.youyuan.dnamic;

import java.util.Arrays;

/**
 * @author zhangyu
 * @version 1.0
 * @description 动态规划算法应用场景 背包问题
 * <p>
 * 计算方式通过二维数组，行为物品  0-i   列为背包重量  0-j
 * <p>
 * w[i]  第i个商品的重量
 * val[i]  第i个上篇的价格
 * v[i][j] 第i个商品的重量为j的价格
 * <p>
 * 思路：
 * 1.  w[i]>j时  v[i][j]=v[i-1][j]如果当前商品重量大于背包重量时，当前最大价格就等于上一个单元格的价格
 * 2.  w[i]<j时  v[i][j]=Math.max(v[i-1][j],val[i]+v[i-1][j-w[i-1]])
 * @date 2019/8/22 15:19
 */
public class Knapsack {

    public static void main(String[] args) {
        int[] w = new int[]{1, 4, 3};  //商品重量
        int[] val = new int[]{1500, 3000, 2000};  //商品价格
        int n = 4;  //背包的重量
        int[][] v = new int[w.length + 1][n + 1];  //定义二维数组
        int[][] path = new int[w.length + 1][n + 1];  //用于存储放入背包的商品

        //初始化二维数组  使第一行为0  第一列为0

        //第一行为0
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        //第一列为0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }

        //开始遍历往背包添加
        for (int i = 1; i < v.length; i++) {  //从第一行开始
            for (int j = 1; j < v[i].length; j++) {  //从第一列开始
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
//                    v[i][j] = Math.max(v[i - 1][j], val[i-1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //遍历二维数组
        for (int[] ints : v) {
            System.out.println(Arrays.toString(ints));
        }

        System.out.println("===============");

        int i = path.length - 1; //行的最大下标
        int j = path[0].length - 1;  //列的最大下标
        while(i > 0 && j > 0 ) { //从path的最后开始找
            if(path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i-1]; //w[i-1]
            }
            i--;
        }


    }

}
