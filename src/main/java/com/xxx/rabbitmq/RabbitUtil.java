package com.xxx.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;

/**
 * @See https://juejin.cn/post/7132268340541653005
 * 封装工厂类
 */
public class RabbitUtil {
    public static ConnectionFactory getConnectionFactory() {
        // 创建连接工程，下面给出的是默认的case
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/");
        return factory;
    }
}
