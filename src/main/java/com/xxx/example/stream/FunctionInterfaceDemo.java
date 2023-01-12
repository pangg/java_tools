package com.xxx.example.stream;

import java.math.BigDecimal;
import java.util.function.*;

/**
 * 常用函数式接口
 */
public class FunctionInterfaceDemo {
    public static void main(String[] args) {
        /**
         * 判断真假
         * Predicate.test(T t)
         * @return boolean
         */
        Predicate<Integer> predicate = x -> x > 185;
        Student student = new Student("9龙", 23, 175);
        System.out.println("9龙的身高高于185吗？：" + predicate.test(student.getStature()));

        /**
         * 消费消息
         * Consumer.accept(T t)
         * @return void
         */
        Consumer<String> consumer = System.out::println;
        consumer.accept("好好学学");

        /**
         * Function
         * 将T映射为R（转换功能）
         * R apply(T t)
         * @return R
         */
        Function<Student, String> function = Student::getName;
        String name = function.apply(student);
        System.out.println(name);

        /**
         * Supplier
         * 生产消息
         * T get()
         * @Return T
         */
        Supplier<Integer> supplier = () -> Integer.valueOf(BigDecimal.TEN.toString());
        System.out.println(supplier.get());

        /**
         * UnaryOperator
         * 一元操作
         * T apply(T t)
         * @Return T
         */
        UnaryOperator<Boolean> unaryOperator = uglily -> !uglily;
        Boolean apply2 = unaryOperator.apply(true);
        System.out.println(apply2);

        /**
         * BinaryOperator
         * 二元操作
         * apply(T t, U u)
         * @Return T
         */
        BinaryOperator<Integer> operator = (x, y) -> x * y;
        Integer integer = operator.apply(2, 3);
        System.out.println(integer);

        test(() -> "我是一个演示的函数式接口");
    }

    /**
     * 演示自定义函数式接口使用
     * @param worker
     */
    public static void test(Worker worker) {
        String work = worker.work();
        System.out.println(work);
    }

    public interface Worker {
        String work();
    }
}
