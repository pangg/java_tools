package com.xxx.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 封装消费者
 *
 *   1. chanel.exchangeBind()
 *      Exchange.BindOk exchangeBind(String destination, String source, String routingKey) throws IOException;
 *      生产者发送消息到source交换器中，source根据路由键找到与其匹配的另一个交换器destination，并把消息转发到destination中，存储在destination绑定的队列queue中
 *
 */
public class MsgConsumer {
    public static void consumerMsg(String exchange, String queue, String routingKey)
            throws IOException, TimeoutException {
        ConnectionFactory factory = RabbitUtil.getConnectionFactory();

        Connection connection = factory.newConnection();

        // 创建消息信道
        final Channel channel = connection.createChannel();

        /**
         * channel.queueDeclare()
         * Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) throws IOException;
         * 参数：
         *      queue: 队列名称
         *      durable： 是否持久化, 队列的声明默认是存放到内存中的，如果rabbitmq重启会丢失，如果想重启之后还存在就要使队列持久化，保存到Erlang自带的Mnesia数据库中，当rabbitmq重启之后会读取该数据库
         *      exclusive：是否排外的，有两个作用，一：当连接关闭时connection.close()该队列是否会自动删除；二：该队列是否是私有的private，
         *          如果不是排外的，可以使用两个消费者都访问同一个队列，没有任何问题，如果是排外的，会对当前队列加锁，其他通道channel是不能访问的，如果强制访问会报异常，一般等于true的话用于一个队列只能有一个消费者来消费的场景
         *      autodelete：当没有任何消费者使用时，自动删除该队列
         *      arguments：扩展参数。如：x-message-ttl
         */
        // 消息队列
        channel.queueDeclare(queue, true, false, false, null);

        /**
         * AMQP.Queue.BindOk queueBind(String queue , String exchange , String routingKey ) throws IOException;
         *      参数：
         *      queue 队列名称
         *      exchange 交换机名称
         *      routingKey 路由key
         */
        // 绑定队列到交换机
        channel.queueBind(queue, exchange, routingKey);
        System.out.println("[*] Waiting for message. To exist press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                try {
                    System.out.println(" [x] Received '" + message);
                } finally {
                    System.out.println(" [x] Done");
                    /**
                     * void basicAck(long deliveryTag, boolean multiple) throws IOException;
                     *      deliveryTag：该消息的index
                     *      multiple：是否批量处理.true:将一次性ack所有小于deliveryTag的消息
                     */
                    // 消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息（成功消费，消息从队列中删除 ）
                    channel.basicAck(envelope.getDeliveryTag(), false);

                    /**
                     * void basicNack(long deliveryTag, boolean multiple, boolean requeue) throws IOException;
                     * 参数：
                     *      deliveryTag:该消息的index
                     *      multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息
                     *      requeue：被拒绝的是否重新入队列 注意：如果设置为true ，则会添加在队列的末端
                     */
                    // ack返回false，requeue-true并重新回到队列
                    // channel.basicNack(envelope.getDeliveryTag(), false, true);
                }
            }
        };

        /**
         * String basicConsume(String queue, boolean autoAck, Consumer callback) throws IOException;
         * 参数：
         *      queue：队列名称
         *      autoAck：是否自动ack，如果不自动ack，需要使用channel.ack、channel.nack、channel.basicReject 进行消息应答
         *      callback：回调函数
         */
        // 取消自动ack
        channel.basicConsume(queue, false, consumer);

    }
}
