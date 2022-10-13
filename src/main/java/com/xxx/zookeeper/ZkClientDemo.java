package com.xxx.zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * 第二种实现：
 *      说明：
 *          1.subscribe开头的为注册监听的一些方法
 *          2.addAuthInfo和setAcl为权限相关控制
 *          3.普通使用这种方式还是值得推荐的
 */
public class ZkClientDemo {
    public static void main(String[] args) throws InterruptedException {
        String connStr = "127.0.0.1:2181";
        ZkClient zk = new ZkClient(connStr);

        String path = "/zhuzhu2";

        // 注册【数据】事件
        zk.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("数据修改：" + s + "-----" + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("删除数据：" + s);
            }
        });

        zk.subscribeChildChanges("/top", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("子节点发生变化：" + s);
                list.forEach(f -> {
                    System.out.println("content:" + f);
                });
            }
        });

        List<String> list = zk.getChildren("/");
        list.forEach(System.out::println);

        String res = zk.create(path, "I love you", CreateMode.PERSISTENT);
        System.out.println("创建节点"+path+"成功：" + res);

        zk.writeData(path, "forever");
        System.out.println("修改节点"+path+"数据成功");

        Thread.sleep(1000);

        res = zk.readData(path);
        System.out.println("节点数据：" + res);

        Thread.sleep(1000);

        zk.delete(path);
        System.out.println("删除节点" + path + "成功");

        System.out.println("-------------------");

        /*for (int i = 0; i < 10; i++) {
            zk.create(path, "I love you", CreateMode.PERSISTENT);
            Thread.sleep(100);
            zk.delete(path);
            Thread.sleep(100);
        }*/
    }
}
