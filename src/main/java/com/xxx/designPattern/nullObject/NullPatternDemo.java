package com.xxx.designPattern.nullObject;

/**
 * 空对象模式
 *      在“空对象”模式中，空对象将替换NULL对象实例的检查。而不是检查一个空值，Null对象反映一个无关的关系(即什么也不做)。 这种Null对象还可以用于在数据不可用时提供默认行为。
 *
 * @See https://www.yiibai.com/design_pattern/null_object_pattern.html
 */
public class NullPatternDemo {
    public static void main(String[] args) {
        AbstractCustomer customer1 = CustomerFactory.getCustomer("Rob");
        AbstractCustomer customer2 = CustomerFactory.getCustomer("Bob");
        AbstractCustomer customer3 = CustomerFactory.getCustomer("Julie");
        AbstractCustomer customer4 = CustomerFactory.getCustomer("Laura");

        System.out.println("Customers");
        System.out.println(customer1.getName());
        System.out.println(customer2.getName());
        System.out.println(customer3.getName());
        System.out.println(customer4.getName());
    }
}
