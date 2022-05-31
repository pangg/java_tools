package com.xxx.dataStructure;

import org.junit.Test;

public class QuickSortTest {

    @Test
    public void quickSort2() {
        int[] arr = {10,7,2,4,7,62,3,4,2,1,8,9,19};

        QuickSort.quickSort2(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void teseQuickSort(){
        int[] array = {3,5,7,3,8,9,6,1,0};
        QuickSort qs = new QuickSort(array);
        qs.sort();
        qs.printSort();
    }
}