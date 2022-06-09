package com.xxx.dataStructure;

import org.junit.Test;

import static org.junit.Assert.*;

public class MySortTest {

    @Test
    public void selectSort() {
        int[] a = {5,1,12,3,125,50,66};
        MySort.selectSort(a);
    }

    @Test
    public void insertSort() {
        int[] a = {5,1,12,3,125,50,66};
        MySort.selectSort(a);
    }

    @Test
    public void bubbleSort() {
        int[] a = {5,1,12,3,125,50,66};
        MySort.bubbleSort(a);
    }

    @Test
    public void xierSort() {
        int[] a = {5,1,12,3,125,50,66};
        MySort.xierSort(a);
    }

    @Test
    public void mergeSort() {
        int[] a = {5,1,12,3,125,50,66};
        MySort.mergeSort(a, 0, a.length - 1);
    }

    @Test
    public void fastSort() {
        int[] a = {5,1,12,3,125,50,66};
        MySort.fastSort(a);
    }
}