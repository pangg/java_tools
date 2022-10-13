package com.xxx.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * Java操作zookeeper总共有三种方式：
 *
 * 1.原生的Java API
 *
 * 2.zkclient
 *
 * 3.curator
 */

/**
 * 说明：
 *
 * 1.会话连接是异步的，需要自己去处理。此处用的CountDownLatch
 *
 * 2.Watch需要重复注册，不然就不能生效，比如开始的zookeeper.exists("/top/jinyong", watcher);就是为了注册监听
 *
 * 3.开发的复杂性还是比较高的
 *
 * 4.不支持多节点删除和创建。需要自己去递归。后面有一个关于递归的示例。
 */
public class JavaApi {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String connStr = "127.0.0.1:2181";
        CountDownLatch countDown = new CountDownLatch(1);

        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    System.err.println("eventType:" + watchedEvent.getType());
                    if (watchedEvent.getType() == Event.EventType.None) {
                        countDown.countDown();
                    } else if (watchedEvent.getType() == Event.EventType.NodeCreated) {
                        System.out.println("listen:节点创建");
                    } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                        System.out.println("listen:子节点修改");
                    }
                }
            }
        };

        ZooKeeper zooKeeper = new ZooKeeper(connStr, 5000, watcher);
        countDown.countDown();

        String path = "/top";
        // 注册监听，每次都要重新注册，否则监听不到
        zooKeeper.exists(path, watcher);

        // 创建节点
        String result = zooKeeper.create(path, "一生一世".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(result);

        Thread.sleep(10);

        // 获取节点
        byte[] bs = zooKeeper.getData(path, true, null);
        result = new String(bs);
        System.out.println("创建节点后的数据是：" + result);

        // 修改节点
        zooKeeper.setData(path, "I love you".getBytes(), -1);

        Thread.sleep(10);

        bs = zooKeeper.getData(path, true, null);
        result = new String(bs);
        System.out.println("修改节点后的数据是：" + result);

        // 删除节点
        zooKeeper.delete(path, -1);
        System.out.println("节点删除成功");
    }
}
