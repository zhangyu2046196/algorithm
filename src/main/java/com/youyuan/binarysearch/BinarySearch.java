package com.youyuan.binarysearch;

/**
 * @author zhangyu
 * @version 1.0
 * @description 二分查找算法 (非递归)
 * @date 2019/8/21 21:15
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearchData(arr, 100);
        System.out.println("index:" + index);
    }

    /**
     * 二分查找
     *
     * @param arr    待查找数组，要求是有序数组
     * @param target 要查找的数
     * @return 返回查找的数在数组中的下标， 不存在返回-1
     */
    public static int binarySearchData(int[] arr, int target) {
        int left = 0;//二分查找左下标
        int right = arr.length - 1;//二分查找右下标
        while (left <= right) {
            int mid = (left + right) / 2;  //数组中间下标
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) { //向左查找
                right = mid - 1;
            } else {  //向右查找
                left = mid + 1;
            }
        }
        return -1;
    }

}
