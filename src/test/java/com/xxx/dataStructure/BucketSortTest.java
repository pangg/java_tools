package com.xxx.dataStructure;

import org.junit.Test;

import java.util.Arrays;

public class BucketSortTest {
    @Test
    public void testDemo() {
        int[] array = {5,7,3,5,4,8,6,4,1,2};
        BucketSort bs = new BucketSort(10, array);
        bs.sort();
        System.out.println(bs.getBuckets()[7]);
        bs.sortOut();
    }
}