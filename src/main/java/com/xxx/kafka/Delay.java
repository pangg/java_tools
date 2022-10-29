package com.xxx.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.xml.crypto.Data;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @See https://juejin.cn/post/7114228631735861261
 * Kafka消息延时实现思路
 *      生产者将生产消息存入topic_delay主题中进行存储。
 *      将topic_delay主题中的所有消息拉取至ConcurrentLinkedQueue队列中。
 *      取值判断是否满足延时要求。
 *          a. 如果满足延时要求，则将消息生产至topic_out主题中，并将queue队列中的值移除。
 *          b. 如果不满足延时要求，则等待自定义时间后重试判断。
 *      消费者最终从topic_out主题中拉取消息进行消费。
 */
public class Delay {
    // 缓存队列
    public static ConcurrentLinkedQueue<ConsumerRecord<String, String>> link = new ConcurrentLinkedQueue<>();
    //延迟时间(20秒)，可根据需要设置延迟大小
    public static long delay = 20000L;

    /**
     * 入口
     * @param args
     */
    public static void main(String[] args) {
        //延时主题（用于控制延时缓冲）
        String topic_delay = "topic_delay";
        //输出主题(直接供消费者消费)
        String topic_out = "topic_out";
        /*
        消费线程
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                //消费者配置。请根据需要自行设置Kafka配置
                Properties props = new Properties();
                props.setProperty("bootstrap.servers", "localhost:9092");
                props.setProperty("group.id", "test");
                props.setProperty("enable.auto.commit", "true");
                props.setProperty("auto.commit.interval.ms", "1000");
                props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                //创建消费者
                KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

                // 指定消费主题
                consumer.subscribe(Arrays.asList(topic_delay));
                while (true) {
                    // 轮询消费
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10));
                    // 遍历当前轮询批次拉取到的消息
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println(record);
                        //将消息添加到缓存队列
                        link.add(record);
                    }
                }
            }
        }).start();

        /**
         * 生产线程
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                //生产者配置（请根据需求自行配置）
                Properties props = new Properties();
                props.put("bootstrap.servers", "localhost:9092");
                props.put("linger.ms", 1);
                props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                //创建生产者
                Producer<String, String> producer = new KafkaProducer<String, String>(props);
                //持续从缓存队列中获取消息
                while (true) {
                    // 如果缓存队列为空则放缓取值速度
                    if (link.isEmpty()) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }

                    // 获取缓存队列栈顶消息
                    ConsumerRecord<String, String> record = link.peek();
                    // 获取该消息时间戳
                    long timestamp = record.timestamp();
                    Date now = new Date();
                    long nowTime = now.getTime();
                    if (timestamp + Delay.delay < nowTime) {
                        // 获取消息值
                        String value = record.value();
                        // 生产者发送消息到输出主题
                        producer.send(new ProducerRecord<String, String>(topic_out, "", value));
                        // 从缓存队列中移除消息
                        link.poll();
                    } else {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
