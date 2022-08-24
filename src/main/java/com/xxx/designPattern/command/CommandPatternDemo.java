package com.xxx.designPattern.command;

/**
 * 命令模式
 * 属于行为模式类别。 请求作为命令包装在一个对象下，并传递给调用器对象。
 * 调用者对象查找可以处理此命令的适当对象，并将命令传递到执行命令的相应对象。
 */
public class CommandPatternDemo {
    public static void main(String[] args) {
        Stock abcStock = new Stock();

        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }
}
