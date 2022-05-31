package com.xxx.dataStructure;

import org.junit.Test;

public class InsertSortTest {

    @Test
    public void sort() {
        int[] numbers = {5,3,2,6,4};
        InsertSort insertSort = new InsertSort(numbers);
        insertSort.sort();
    }
}