package com.xxx.designPattern.compositeEntity;

/**
 * 组合实体模式
 * @See https://www.yiibai.com/design_pattern/composite_entity_pattern.html
 */
public class CompositeEntityPatternDemo {
    public static void main(String[] args) {
        Client client = new Client();
        client.setData("Test", "Data");
        client.printData();
        client.setData("Second Test", "Data1");
        client.printData();
    }
}
