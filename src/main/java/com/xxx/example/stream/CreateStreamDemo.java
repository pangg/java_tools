package com.xxx.example.stream;

import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * 创建流
 */
public class CreateStreamDemo {
    /**
     * 通过stream方法把List或数组转换为流
     */
    @Test
    public void testStream() {
        Arrays.asList("a1", "a2", "a3").stream().forEach(System.out::println);
        // Stream.of("aa1", "aa2", "aa3").forEach(System.out::println);
        Arrays.stream(new int[]{1, 2, 3}).forEach(System.out::println);
    }

    /**
     * 通过Stream.of方法直接传入多个元素构成一个流
     */
    @Test
    public void testOf() {
        String[] arr = {"a", "b", "c"};
        Stream.of(arr).forEach(System.out::println);
        Stream.of("a", "b", "c").forEach(System.out::println);
        Stream.of(1, 2, "a").map(item -> item.getClass().getName()).forEach(System.out::println);
    }

    /**
     * 通过Stream.iterate方法使用迭代的方式构造一个无限流，然后使用limit限制流元素个数
     */
    @Test
    public void testIterate() {
        Stream.iterate(2, item -> item * 2).limit(10).forEach(System.out::println);
        Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.TEN)).limit(10).forEach(System.out::println);
    }

    /**
     * 通过Stream.generate方法从外部传入一个提供元素的Supplier来构造无限流，然后使用limit限制流元素个数
     */
    @Test
    public void testGenerate() {
        Stream.generate(() -> "test").limit(3).forEach(System.out::println);
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    /**
     * 通过IntStream或DoubleStream构造基本类型的流
     */
    @Test
    public void testPrimitive() {
        //演示IntStream和DoubleStream
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.range(0, 3).mapToObj(i -> "x").forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
        DoubleStream.of(1.1, 2.2, 3.3).forEach(System.out::println);

        //各种转换，后面注释代表了输出结果
        System.out.println(IntStream.of(1, 2).toArray().getClass());
        System.out.println(Stream.of(1, 2).mapToInt(Integer::intValue).toArray().getClass());
        System.out.println(IntStream.of(1, 2).boxed().toArray().getClass());
        System.out.println(IntStream.of(1, 2).asDoubleStream().toArray().getClass());
        System.out.println(IntStream.of(1, 2).asLongStream().toArray().getClass());

        //注意基本类型流和装箱后的流的区别
        List<String> list = Arrays.asList("a", "b", "c").stream()   // Stream<String>
                .mapToInt(String::length)       // IntStream
                .asLongStream()                 // LongStream
                .mapToDouble(x -> x / 10.0)     // DoubleStream
                .boxed()                        // Stream<Double>
                .mapToLong(x -> 1L)             // LongStream
                .mapToObj(x -> "")              // Stream<String>
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void testPeek() {
        List<Integer> firstPeek = new ArrayList<>();
        List<Integer> secondPeek = new ArrayList<>();
        List<Integer> result = IntStream.rangeClosed(1, 10)
                .boxed()
                .peek(firstPeek::add)
                .filter(i -> i > 5)
                .peek(secondPeek::add)
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("firstPeek:" + firstPeek);
        System.out.println("secondPeek:" + secondPeek);
        System.out.println("result:" + result);
    }

    /**
     * 自定义搜集器：实现一个 MostPopularCollector，来得到 List 中出现次数最多的元素
     */
    @Test
    public void testMostPopularCollector() {
        assertThat(Stream.of(1, 1, 2, 2, 2, 3, 4, 5, 5).collect(new MostPopularCollector<>()).get(), is(2));

        assertThat(Stream.of('a', 'b', 'c', 'c', 'c', 'd').collect(new MostPopularCollector<>()).get(), is('c'));
    }
}
