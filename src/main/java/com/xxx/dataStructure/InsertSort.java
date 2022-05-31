package com.xxx.dataStructure;

import java.util.Arrays;

/**
 * 插入排序
 * 过程：
 *      取第一个元素作为有序序列基准；
 *      第一层循环从第二个元素开始遍历；
 *      外层循环的每一个元素，都会依次和前面有序序列的元素对比（倒序对比），然后找到合适位置后插入；
 *      如数组：[5,  3, 2, 6, 4]
 *      第一次排序：[3, 5,  2, 6, 4]
 *      第二次排序：[2, 3, 5,  6, 4]
 *      第三次排序：[2, 3, 5, 6,  4]
 *      排序后的结果为：[2, 3, 4, 5, 6]
 */
public class InsertSort {
    private int[] array;

    public InsertSort(int[] array) {
        this.array = array;
    }

    public void sort() {
        System.out.println("排序前的结果为：" + Arrays.toString(array));
        for (int i = 1; i < array.length; i++) {  // 控制循环轮数
            int temp = array[i];  // 定义待交换元素
            int j;
            for (j = i; j > 0 && temp < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = temp;
            System.out.println("第" + i + "轮的排序结果为：" + Arrays.toString(array));
        }
        System.out.println("排序后的结果为：" + Arrays.toString(array));
    }
}
