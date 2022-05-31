package com.xxx.dataStructure;

/**
 * 桶排序:
 *      声明一个长度为10的数组作为10个桶，然后将成绩逐一往桶中放时，该桶的值+1，
 *      最终输出倒序输出数组下标，数组每个位置的值为几就输出几次，这样就能实现基本的桶排序。
 */
public class BucketSort {
    private int[] buckets;
    private int[] array;

    public BucketSort(int range, int[] array) {
        this.buckets = new int[range];
        this.array = array;
    }

    /**
     * 排序
     */
    public void sort(){
        if (array != null && array.length > 1) {
            // 以元素为key，放入桶中
            for (int i = 0; i < array.length; i++) {
                buckets[array[i]] ++;  // 记录相同元素数量
            }
        }
    }

    /**
     * 排序输出
     */
    public void sortOut() {
        // 倒序输出数据
        for (int i = buckets.length - 1; i >= 0; i--) {  // 桶key编号倒序（从大到小）
            for (int j = 0; j < buckets[i]; j++) {   // 桶中有几个元素就输出几次
                System.out.print(i + "\t");
            }
        }
    }

    public int[] getBuckets() {
        return buckets;
    }
}
