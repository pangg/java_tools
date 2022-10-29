package com.xxx.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Fanout方式（指定队列）
 *
 * Fanout Exchange：这种类型的交换机需要将队列绑定到交换机上。一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上。
 * 很像子网广播，每台子网内的主机都获得了一份复制的消息。简单点说就是发布订阅。
 *
 * 使用Fanout方式时，有几个点需要注意一下：
 *      生产者的routkey可以为空，因为生产者的所有数据，会下放到每一个队列，所以不会通过routkey去路由；
 *      消费者需要指定queues，因为消费者需要绑定到指定的queues才能消费。
 *
 * @See https://juejin.cn/post/7132268340541653005
 */
public class FanoutDemo {
    private static final String EXCHANGE_NAME = "fanout.exchange";

    public void publicMsg(String routingKey, String msg) {
        try {
            MsgProducer.publishMsg(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, routingKey, msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fanoutProducer() throws InterruptedException {
        FanoutDemo fanoutProducer = new FanoutDemo();
        String msg = "hello fanout >>>";
        for (int i = 0; i < 10; i++) {
            fanoutProducer.publicMsg("", msg + i);
        }
        Thread.sleep(1000 * 30);
    }

    @Test
    public void fanoutConsumer() throws InterruptedException {
        FanoutDemo fanoutConsumer = new FanoutDemo();
        String[] queueNames = new String[]{"qa-2", "qb-2", "qc-2"};
        for (int i = 0; i < 3; i++) {
            fanoutConsumer.msgConsumer(queueNames[i], "");
        }
        Thread.sleep(1000 * 30);
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
