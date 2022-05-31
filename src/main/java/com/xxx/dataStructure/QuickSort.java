package com.xxx.dataStructure;

/**
 * 快速排序：
 *      通过一趟排序将要排序的数据分割为两部分，第一部分所有数据比第二部分的所有数据小，
 *      按照这种思路将两部分数据再次分别进行快速排序，可以使用递归完成，最终使得整个数据序列有序。
 *
 * 参考： https://www.jb51.net/article/211611.htm
 */
public class QuickSort {
    private int[] array;
    public QuickSort(int[] array) {
        this.array = array;
    }

    public void printSort() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public void sort() {
        quickSort(array,0,array.length -1);
    }

    /**
     * 推荐
     * 过程：
     *      选择第一个元素为基准，从数组的两端循环一次数组；
     *      先循环右侧数据（从最后往前），找到比基准数据小的第一个元素；
     *      再循环左侧数据（从前往后），找到比基准数据大的第一元素；
     *      将两个元素位置交换；
     *      按照上面操作完成第一轮循环（当左右循环到同一个位置时），替换该位置和基准位置的数据；
     *      然后从上述循环结束位置将数组一分为二，重复上述操作
     *
     * 参考：https://www.jb51.net/article/211611.htm
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort2(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        // temp就是基准位
        temp = arr[low];

        while (i < j) {
            // 先看右边，依次往左递减
            while (temp <= arr[j] && i < j) {
                j --;
            }
            // 再看左边，依次往右递增
            while (temp >= arr[i] && i < j) {
                i ++;
            }

            // 如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        // 最后将基准位置的值 与 i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;

        // 递归调用左半数组
        quickSort2(arr, low, j - 1);
        // 递归调用右半数组
        quickSort2(arr, j + 1, high);
    }

    // ???
    private void quickSort(int[] array, int begin, int end) {
        if (begin < end) {   // i和j没相遇之前比较各数据与基准值大小
            int base = array[begin];  // 取第一个值为基准值
            int i = begin;  // 左标记为i
            int j = end;    // 右标记为j

            // 一趟排序，找到比基准值大的在基准值右，比基准值小的在基准值左
            while (i < j) {
                // 从右往左扫描
                while (i < j && array[j] > base) {  // 从右往左扫描，如果元素比基准值大
                    j --;  // 则右边标记--，直到找到第一个比基准值小的，停止扫描
                }
                if (i < j) {
                    array[i] = array[j];  // 交换右扫描第一个比基准值小的数
                    i ++;  // i标记右移一位
                }

                // 从左往右扫描
                while(i < j && array[i] < base){//从左往右扫，如果元素比基准值小
                    i ++;  //则左标记++，直到找到第一个比基准值大的，停止扫描
                }
                if(i < j){
                    array[j] = array[i];  //交换左扫描第一个比基准值大的数
                    j--;  //j标记左移一位
                }
            }
            array[i] = base;  //此时i为中间位置k

            quickSort(array,begin,i-1);  //左侧按照快排思路，递归

            quickSort(array,i+1,end);    //右侧按照快排思路，递归
        }
    }
}
