package com.xxx.example.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayDemo {
    /**
     * 数组排序和搜索
     */
    @Test
    public void sortAndSearch() {
        int array[] = { 2, 5, -2, 6, -3, 8, 0, -7, -9, 4 };
        Arrays.sort(array);
        printArray("Sorted array", array);
        int index = Arrays.binarySearch(array, 2);
        System.out.println("Found 2 @ " + index);
    }

    private static void printArray(String message, int array[]) {
        System.out.println(message + ": [length: " + array.length + "]");
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(array[i]);
        }
        System.out.println();
    }

    /**
     * 线性搜索
     */
    @Test
    public void sortAndSearch2() {
        int[] a = { 2, 5, -2, 6, -3, 8, 0, -7, -9, 4 };
        int target = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) {
                System.out.println("Element found at index " + i);
                break;
            }
        }
    }

    @Test
    public void sortAndSearch3() {
        int arr[] = { 2, 5, -2, 6, -3, 8, 0, -7, -9, 4 };
        System.out.println("Array Before Bubble Sort");

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        bubbleSort(arr);
        System.out.println("Array After Bubble Sort");

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 设定二维数组的上限
     */
    @Test
    public void dimensionOfArray() {
        String[][] data = new String[2][5];
        System.out.println("Dimension 1: " + data.length);
        System.out.println("Dimension 2: " + data[0].length);
    }

    /**
     * 数组反转
     */
    @Test
    public void reverseArr() {
        String[] arr = {"A", "B", "C", "D"};
        List<String> listArr = Arrays.asList(arr);
        System.out.println(listArr.toString());
        Collections.reverse(listArr);
        System.out.println(listArr.toString());
    }

    /**
     * 数组遍历输出
     */
    @Test
    public void arrayOutput() {
        String[] greeting = new String[3];
        greeting[0] = "This is the greeting";
        greeting[1] = "for all the readers from";
        greeting[2] = "Java Source .";

        for (int i = 0; i < greeting.length; i++) {
            System.out.println(greeting[i]);
        }

        System.out.println("-----------line------------");

        String[][] deepArr = new String[][] { { "Max", "Min" },
                { "Sum", "Avg" } };
        System.out.println(Arrays.toString(deepArr));
        System.out.println(Arrays.deepToString(deepArr));
    }

    /**
     * 搜索数组中的最小值和最大元素
     */
    @Test
    public void arraySearchMaxMin() {
        int[] numbers = new int[] { 28, 21, 17, 41, 34, 29, 15 };
        int s = numbers[0];
        int l = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > l)
                l = numbers[i];
            else if (numbers[i] < s)
                s = numbers[i];
        }
        System.out.println("Largest Number is : " + l);
        System.out.println("Smallest Number is : " + s);

        System.out.println("--------------------------");

        Integer[] nums = { 18, 22, 7, 15, 40, 19, 25 };
        int min = (int) Collections.min(Arrays.asList(nums));
        int max = (int) Collections.max(Arrays.asList(nums));
        System.out.println("Min number: " + min);
        System.out.println("Max number: " + max);
    }

    /**
     * 合并两个数组
     */
    @Test
    public void mergeTwoArrays() {
        String[] a = { "y", "i", "i" };
        String[] b = { "b", "a", "i" };

        List<String> list = new ArrayList<>(Arrays.asList(a));
        list.addAll(Arrays.asList(b));
        Object[] c = list.toArray();
        System.out.println(Arrays.toString(c));
    }

    @Test
    public void mergeTwoArrays2() {
        int[] a = { 1, 2, 3, 4 };
        int[] b = { 55, 66, 77, 88, 99, 100 };
        int[] c = new int[a.length + b.length];
        int count = 0;

        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
            count++;
        }
        for (int j = 0; j < b.length; j++) {
            c[count++] = b[j];
        }
        for (int i = 0; i < c.length; i++)
            System.out.print(c[i] + " ");
    }

    /**
     * 数组填充和初始化
     * Array.fill(arrayname，value)方法和Array.fill(arrayname，starting index，ends index，value)方法填充(初始化数组的所有元素)一个数组
     */
    @Test
    public void arrayFilling() {
        int array[] = new int[6];
        Arrays.fill(array, 100);

        for (int i = 0, n = array.length; i < n; i++) {
            System.out.print(array[i]+",");
        }

        System.out.println("\n===========================");
        Arrays.fill(array, 3, 6, 99); // 从索引3到6使用数字99填充

        for (int i = 0, n = array.length; i < n; i++) {
            System.out.print(array[i]+",");
        }
    }

    /**
     * 数组扩展
     */
    @Test
    public void extendingArray() {
        String[] names = new String[] { "A", "B", "C" };
        String[] extended = new String[5];
        extended[3] = "D";
        extended[4] = "E";
        System.arraycopy(names, 0, extended, 0, names.length);

        for (String str : extended) {
            System.out.print(str+",");
        }
    }

    /**
     * 数组搜索和比较
     */
    @Test
    public void arraySortCompare() {
        int array[] = { 12, 15, -2, 16, -3, 28, 10, -7, -9, 24 };
        Arrays.sort(array);
        printArray("Sorted array", array);

        int index = Arrays.binarySearch(array, 2);
        System.out.println("Found 2 @ " + index);

        System.out.println("-------------------");

        int arr1[] = { 1, 2, 3 };
        int arr2[] = { 1, 2, 3 };

        if (arr1 == arr2)
            System.out.println("Same");
        else
            System.out.println("Not same");

        System.out.println("------------------");

        if (Arrays.equals(arr1, arr2))
            System.out.println("Same");
        else
            System.out.println("Not same");
    }

    /**
     * 删除数组中的元素
     */
    @Test
    public void removeArrayElement() {
        ArrayList<String> objArray = new ArrayList();
        objArray.clear();
        objArray.add(0, "0th element");
        objArray.add(1, "1st element");
        objArray.add(2, "2nd element");
        System.out.println("Array before removing an element" + objArray);
        objArray.remove(1);
        objArray.remove("0th element");
        System.out.println("Array after removing an element" + objArray);

        System.out.println("----------------------");

        ArrayList<Integer> arr = new ArrayList<Integer>(5);
        arr.add(220);
        arr.add(115);
        arr.add(320);
        arr.add(145);

        System.out.println("Size of list: " + arr.size());
        for (Integer number : arr) {
            System.out.println("Number = " + number);
        }
        arr.remove(2);

        System.out.println("After remove, Size of list: " + arr.size());

        for (Integer number : arr) {
            System.out.println("Number = " + number);
        }
    }

    /**
     * 从数组中查找公共的元素
     */
    @Test
    public void findCommonElements() {
        ArrayList<String> objArray = new ArrayList();
        ArrayList<String> objArray2 = new ArrayList();
        objArray2.add(0, "common1");
        objArray2.add(1, "common2");
        objArray2.add(2, "notcommon");
        objArray2.add(3, "notcommon1");
        objArray.add(0, "common1");
        objArray.add(1, "common2");
        objArray.add(2, "notcommon2");
        System.out.println("Array elements of array1" + objArray);
        System.out.println("Array elements of array2" + objArray2);
        objArray.retainAll(objArray2);
        System.out
                .println("Array1 after retaining common elements of array2 & array1"
                        + objArray);

        System.out.println("----------------------");

        int[] arr1 = { 14, 17, 13, 19, 12 };
        int[] arr2 = { 13, 12, 112, 19, 400, 32, 14 };

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    System.out.println(arr1[i]);
                }
            }
        }
    }

    /**
     * 从数组中查找对象元素
     */
    @Test
    public void arrayFindObject() {
        ArrayList<String> objArray = new ArrayList<>();
        ArrayList<String> objArray2 = new ArrayList<>();
        objArray2.add(0, "common1");
        objArray2.add(1, "common2");
        objArray2.add(2, "notcommon");
        objArray2.add(3, "notcommon1");
        objArray.add(0, "common1");
        objArray.add(1, "common2");
        System.out.println("Array elements of array1" + objArray);
        System.out.println("Array elements of array2" + objArray2);
        System.out.println("Array 1 contains String common2?? "
                + objArray.contains("common1"));
        System.out.println("Array 2 contains Array1?? "
                + objArray2.contains(objArray));
    }

    /**
     * 比较两个数组是否相等
     */
    @Test
    public void checkArrayEquality() {
        int[] ary = { 1, 2, 3, 4, 5, 6 };
        int[] ary1 = { 1, 2, 3, 4, 5, 6 };
        int[] ary2 = { 1, 2, 3, 4 };
        System.out.println("Is array 1 equal to array 2?? "
                + Arrays.equals(ary, ary1));
        System.out.println("Is array 1 equal to array 3?? "
                + Arrays.equals(ary, ary2));
    }

}
