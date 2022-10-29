package com.xxx.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Direct示例
 *
 * Direct Exchange：见文知意，直连交换机意思是此交换机需要绑定一个队列，要求该消息与一个特定的路由键完全匹配。简单点说就是一对一的，点对点的发送。
 */
public class DirectDemo {
    private static final String EXCHANGE_NAME = "direct.exchange";

    public void publishMsg(String routingKey, String msg) {
        try{
            MsgProducer.publishMsg(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, routingKey, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void directProducer() throws InterruptedException {
        DirectDemo directProducer = new DirectDemo();
        String[] routingKey = new String[]{"aaa", "bbb", "ccc"};
        String msg = "hello >>>";
        for (int i = 0; i < 10; i++) {
            directProducer.publishMsg(routingKey[i % 3], msg + i);
        }
        System.out.println("---------over--------");
        Thread.sleep(1000 * 60 * 100);
    }

    @Test
    public void directConsumer() throws InterruptedException {
        DirectDemo consumer = new DirectDemo();
        String[] routingKey = new String[]{"aaa", "bbb", "ccc"};
        String[] queueNames = new String[]{"qa", "qb", "qc2"};

        for (int i = 0; i < 3; i++) {
            System.out.println(String.format("queue: %s, routingKey: %s", queueNames[i], routingKey[i]));
            consumer.msgConsumer(queueNames[i], routingKey[i]);
        }
        Thread.sleep(1000 * 60 * 100);
    }

    public void msgConsumer(String queueName, String routingKey) {
        try {
            MsgConsumer.consumerMsg(EXCHANGE_NAME, queueName, routingKey);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
