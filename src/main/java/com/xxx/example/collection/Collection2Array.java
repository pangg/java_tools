package com.xxx.example.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 使用Util类的list.add()和list.toArray()方法将集合转换为数组
 */
public class Collection2Array {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("This ");
        list.add("is ");
        list.add("a ");
        list.add("IT ");
        list.add("tutorials ");
        list.add("website. ");

        /**
         * 主要是对比toArray(T[] a)中a的长度和list中的元素个数size
         * 如果a.length>=size, 那么直接调用System.arraycopy方法将elementData中的元素拷贝到a中即可
         * 如果a.length<size, 那么需要 Arrays.copyOf方法进行拷贝, 创建一个长度为size的新数组接收elementData中的元素, 之前传入的数组a已经没用了
         */
        String[] s1 = list.toArray(new String[0]);
        for (int i = 0; i < s1.length; i++) {
            String contents = s1[i];
            System.out.println(contents);
        }

        System.out.println("-----------------");

        List<int[]> list1 = new ArrayList<>();
        list1.add(new int[]{1,2});
        list1.add(new int[]{4,5});
        list1.add(new int[]{7,9});
        list1.add(new int[]{11,15});
        //返回值类型和方法参数一样
        //话句话说, 本来由list存储各个int[], 现在由int[][]存储各个int[]
        //int[][]的长度指定为0或者指定为list.size()都可以.
        int[][] arr = list1.toArray(new int[0][]);
        for(int[] t : arr){
            System.out.println(Arrays.toString(t));
        }
    }
}
