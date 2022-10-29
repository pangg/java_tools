package com.xxx.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 封装生成者
 */
public class MsgProducer {
    public static void publishMsg(String exchange, BuiltinExchangeType exchangeType, String routingKey, String message)
            throws IOException, TimeoutException {
        ConnectionFactory factory = RabbitUtil.getConnectionFactory();
        // 创建连接
        Connection connection = factory.newConnection();

        // 创建消息信道
        Channel channel = connection.createChannel();

        /**
         * exchangeDeclare 各个参数的详细说明：
         *      exchange：交换器的名称
         *      type：交换器的类型，常见的如fanout、direct、topic
         *      durable：设置是否持久化。durable设置为true表示持久化，否则是非持久化。持久化可以将交换器存盘，在服务器重启的时候不会丢失相关的信息。
         *      autoDelete：设置是否自动删除。autoDelete设置为true则表示自动删除。自动删除的前提是至少有一个队列或者交换器与这个交换器绑定，之后所有与这个交换器绑定的队列或者交换器与此解绑。注意不能错误地把这个参数理解为：“当与此交换器连接的宫户端都断开时，RabbitMQ会自动删除本交换器”。
         *      internal：设置是否是内置的。如果设置为true，则表示是内置的交换器，客户端程序无法直接发送消息到这个交换器中，只能通过交换器路由到交换器这种方式。
         *      arguments：其他一些结构化参数，比如alternate-exchange等。
         */
        // 声明exchange中的消息为可持久化，不自动删除
        channel.exchangeDeclare(exchange, exchangeType, true, false, null);
        // 发布消息
        channel.basicPublish(exchange, routingKey, null, message.getBytes());
        System.out.println("Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}
