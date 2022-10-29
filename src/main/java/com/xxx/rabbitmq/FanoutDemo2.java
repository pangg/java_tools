package com.xxx.rabbitmq;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * Fanout方式（随机获取队列）
 * 对于Fanout，我其实根本无需关心队列的名字，如果还指定对应队列进行消费，感觉这个很冗余，所以我们这里就采用随机获取队列名字的方式
 *
 * @See https://juejin.cn/post/7132268340541653005
 */
public class FanoutDemo2 {
    private static final String EXCHANGE_NAME = "fanout.exchange.v2";

    @Test
    public void fanoutProducerV2() throws InterruptedException {
        FanoutDemo2 fanoutProducer = new FanoutDemo2();
        String msg = "hello fanout2 >>>";
        for (int i = 0; i < 1000; i++) {
            fanoutProducer.publishMsg(msg + i);
        }
        Thread.sleep(1000 * 30);
    }

    @Test
    public void fanoutConsumerV2() throws InterruptedException {
        FanoutDemo2 consumer = new FanoutDemo2();
        for (int i = 0; i < 3; i++) {
            consumer.msgConsumer();
        }
        Thread.sleep(1000 * 30);
    }

    public void publishMsg(String msg) {
        try {
            publishMsgV2(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void msgConsumer() {
        try {
            consumerMsgV2(EXCHANGE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void publishMsgV2(String exchange, BuiltinExchangeType exchangeType, String message) throws IOException, TimeoutException {
        ConnectionFactory factory = RabbitUtil.getConnectionFactory();
        //创建连接
        Connection connection = factory.newConnection();

        // 创建消息通道
        Channel channel = connection.createChannel();

        // 声明exchange中的消息
        channel.exchangeDeclare(exchange, exchangeType);

        // 发布消息
        channel.basicPublish(exchange, "", null, message.getBytes(StandardCharsets.UTF_8));

        System.out.println("Sent '" + message + "'");
        channel.close();
        connection.close();
    }

    public static void consumerMsgV2(String exchange) throws IOException, TimeoutException {
        ConnectionFactory factory = RabbitUtil.getConnectionFactory();
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.exchangeDeclare(exchange, BuiltinExchangeType.FANOUT);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, exchange, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
