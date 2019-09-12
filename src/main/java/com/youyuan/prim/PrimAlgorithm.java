package com.youyuan.prim;

import java.util.Arrays;

/**
 * @author zhangyu
 * @version 1.0
 * @description 普里姆算法  解决修路问题(最小生成树MST)
 * <p>
 * 思路：
 * 1.边的数量最少
 * 2.选择的边的权值最小
 * @date 2019/8/27 14:02
 */
public class PrimAlgorithm {

    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertax = data.length;
        //邻接矩阵  10000代表设置一个大值代表不连通其它值代表权值 就是两个村庄的距离
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};

        MGrap mGrap = new MGrap(vertax, data, weight);

        MinTree minTree = new MinTree();

        //遍历邻接矩阵
        minTree.shorGrap(mGrap);

        //获取最小生成树
        minTree.prim(mGrap, 1);

    }

}

/**
 * 最小生成树
 */
class MinTree {

    /**
     * 遍历邻接矩阵
     *
     * @param mGrap 图对象
     */
    public void shorGrap(MGrap mGrap) {
        int[][] weight = mGrap.getWeight();
        for (int[] ints : weight) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 普里姆算法获取最小生成树
     *
     * @param mGrap 图
     * @param v     从下标是v的节点开始查找，来获取最小生成树
     */
    public void prim(MGrap mGrap, int v) {
        //设置最小权值的默认值为10000，最小权值用来存储如A->B  B->C中的最小权值
        int minWeight = 10000;
        //定义数组标记对应下标的节点是否被访问过
        boolean[] visited = new boolean[mGrap.getVertax()];
        //h1 h2存储节点下标
        int h1 = -1, h2 = -1;

        //设置下标为v的节点为已访问
        visited[v] = true;

        //因为最小生成树为的边的数量是节点数量-1，此处节点数量是mGrap.getVertax(),循环的时候d=1
        for (int d = 1; d < mGrap.getVertax(); d++) {

            //因为图是用二维数组构建的，所以遍历二维数组
            for (int i = 0; i < mGrap.getWeight().length; i++) {
                for (int j = 0; j < mGrap.getWeight()[i].length; j++) {
                    //认为i元素是已访问的，i代表行, j代表列,找到边的长度最小的节点下标
                    if (visited[i] && !visited[j] && mGrap.getWeight()[i][j] < minWeight) {
                        minWeight = mGrap.getWeight()[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            //上面循环后找到了一个边，然后进行第二次循环时需要重置变量值
            minWeight = 10000;
            //因为上面i默认为已访问，所以找到边后，把j设为已访问
            visited[h2] = true;

            //打印信息内容
            System.out.println("边<" + mGrap.getData()[h1] + "," + mGrap.getData()[h2] + ">,权值：" + mGrap.getWeight()[h1][h2]);
        }
    }

}

/**
 * 图
 */
class MGrap {
    //顶点个数
    private int vertax;
    //顶点的元素值
    private char[] data;
    //邻接矩阵
    private int[][] weight;

    public MGrap(int vertax, char[] data, int[][] weight) {
        this.vertax = vertax;
        this.data = data;
        this.weight = weight;
    }

    public int getVertax() {
        return vertax;
    }

    public void setVertax(int vertax) {
        this.vertax = vertax;
    }

    public char[] getData() {
        return data;
    }

    public void setData(char[] data) {
        this.data = data;
    }

    public int[][] getWeight() {
        return weight;
    }

    public void setWeight(int[][] weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "MGrap{" +
                "vertax=" + vertax +
                ", data=" + Arrays.toString(data) +
                ", weight=" + Arrays.toString(weight) +
                '}';
    }
}
