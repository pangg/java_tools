package com.xxx.dataStructure;

import java.util.Arrays;

public class MySort {
    /**
     * 快速排序
     * @param a
     */
    public static void fastSort(int[] a) {
        int len = a.length;
        quickSort(a, 0, len - 1);

        System.out.print("结果：");
        for (int v : a) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    private static void quickSort(int[] array, int low, int high) {
        int i = low, j = high;
        int index;    // 这个变量存储基准数
        if (i >= j) {
            return;
        }
        index = array[i];
        while (i < j) {  // 直到i = j，循环结束
            // 从后向前找第一个小于index的数
            while (i < j && array[j] >= index) { // 只要当前的数大于等于index就向前移动下标
                j--;    //向前移动
            }
            if (i < j) {
                array[i++] = array[j]; // 把找到的小于index的数赋值给i所在的位置，然后i向后移动一位
            }

            //前面代码是把整个数组中小于index的数移动到数组前面
            //后面代码是把整个数组中大于等于index的数移动到数组后面

            // 从前向后找第一个大于等于index的数
            while (i < j && array[i] < index) { //只要当前的数小于index就向后移动下标
                i ++;
            }
            if (i < j) {
                array[j--] = array[i]; // 把找到的大于等于index的数赋给j所在的位置，然后j向前移动一位
            }
        }
        array[i] = index;    //最后把index赋给i的位置，也可以写为j，因为此时i = j
        quickSort(array, low, i - 1);    //分治思想：这个递归负责数组位置i前面部分的排序
        quickSort(array, i + 1, high);    //分治思想：这个递归负责数组位置i后面部分的排序
    }

    /**
     * 递归方法，归并排序
     *
     * @param a
     * @param low
     * @param high
     */
    public static void mergeSort(int[] a, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2; // 找到中间下标
            mergeSort(a, low, mid);            // 对前半段再分割
            mergeSort(a, mid + 1, high);    // 对后半段再分割
            merge(a, low, mid, high);        // 排序&合并当前的两段序列
        }

        System.out.print("结果：");
        for (int v : a) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    private static void merge(int[] array, int low, int mid, int high) {
        int i, j, k, n1, n2;
        n1 = mid - low + 1;  // 子序列1的大小
        n2 = high - mid;    // 子序列2的大小
        int[] L = new int[n1];  // 子序列1
        int[] R = new int[n2];  // 子序列2

        // 把数组前半段复制到子序列1:（k为开始复制的下标）
        for (i = 0, k = low; i < n1; i++, k++) {
            L[i] = array[k];
        }

        // 把数组后半段复制到子序列2
        for (i = 0, k = mid + 1; i < n2; i++, k++) {
            R[i] = array[k];
        }

        // 分别扫描子序列1和子序列2，比较谁更小，谁就添加进数组
        for (k = low, i = 0, j = 0; i < n1 && j < n2; k++) {
            if (L[i] < R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
        }

        // 如果子序列1没有扫描完，将其剩下的元素依次添加进数组
        if (i < n1) {
            for (j = i; j < n1; j++, k++) {
                array[k] = L[j];
            }
        }

        // 子序列2也是如此
        if (j < n2) {
            for (i = j; i < n2; i++, k++) {
                array[k] = R[i];
            }
        }
    }

    /**
     * 希尔排序
     *
     * @param a
     */
    public static void xierSort(int[] a) {
        int len = a.length;
        int h = 1;
        while (h < len / 3) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (a[j] < a[j - h]) {
                        exchange(a, j, j - h);
                    }
                }
            }
            h = h / 3;
        }
        System.out.print("结果：");
        for (int v : a) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    /**
     * 冒泡
     *
     * @param a
     */
    public static void bubbleSort(int[] a) {
        int len = a.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    exchange(a, j, j + 1);
                }
            }
            System.out.println((i + 1) + "趟：");
            for (int v : a) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }

    /**
     * 插入排序：循环与前面数据比较，出现小于前面数据则交换
     *
     * @param a
     */
    public static void insertSort(int[] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            for (int j = i; j > 0; j--) {  // 与前面的数比较
                if (a[j] < a[j - 1]) {  // 如果当前小于前面的
                    exchange(a, j, j - 1);
                }
            }
            System.out.println((i + 1) + "趟：");
            for (int v : a) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }

    /**
     * 选择排序: 循环比较出最小值，然后交换
     *
     * @param a
     */
    public static void selectSort(int[] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            int min = i;     // 初始化min索引为当前索引
            for (int j = i + 1; j < len; j++) { // 与后面的比较
                if (a[j] < a[min]) {  // 如果后面下标对应的数值小于min下标对应的数值
                    min = j;  // 后面下标值赋给min
                }
            }
            exchange(a, i, min);   // 经过比较之后，交换当前的与最小的位置
            System.out.println((i + 1) + "趟：");
            for (int v : a) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }

    /**
     * 交换
     *
     * @param a
     * @param i
     * @param j
     */
    private static void exchange(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
