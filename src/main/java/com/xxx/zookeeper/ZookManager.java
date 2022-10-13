package com.xxx.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZookManager {
    ZooKeeper zookeeper = null;

    public ZookManager(String connStr) throws IOException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        zookeeper = new ZooKeeper(connStr, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == Watcher.Event.EventType.None) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    } else {
                        System.out.println("连接失败.");
                        latch.countDown();
                    }
                }
            }

        });
        latch.await();
    }

    /** 创建节点，不存在父节点将新增，如果节点已经存在将抛出异常 **/
    public String create(String path, String val) throws KeeperException, InterruptedException {
        if (!checkPath(path)) {
            return "";
        }

        String p = getParentPath(path);
        cycleCreate(p);

        String url = zookeeper.create(path, val.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        return url;
    }

    /** 设置节点的数据，如果节点不存在将新增该节点  **/
    public Stat setData(String path, String val) throws KeeperException, InterruptedException {
        if (!checkPath(path)) {
            return null;
        }

        cycleCreate(path);
        return zookeeper.setData(path, val.getBytes(), -1);
    }

    /** 删除节点，如果存在子节点将递归删除
     * @throws InterruptedException
     * @throws KeeperException **/
    public void delete(String path) throws KeeperException, InterruptedException {
        if (!checkPath(path)) {
            return;
        }

        List<String> chidren = zookeeper.getChildren(path, false);
        for (String p : chidren) {
            delete(path + "/" + p);
        }
        zookeeper.delete(path, -1);
    }

    private void cycleCreate(String path) throws KeeperException, InterruptedException {
        if (path.isEmpty()) {
            return;
        }
        Stat stat = zookeeper.exists(path, null);
        if (stat == null) {
            String p = getParentPath(path);
            cycleCreate(p);// 递归
            // 创建
            zookeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    /**
     * 检查目录是否正确
     * @param path
     * @return
     */
    private boolean checkPath(String path) {
        if (!path.startsWith("/")) {
            System.err.println("路径必须以/开头:" + path);
            return false;
        }
        if (path.endsWith("/")) {
            System.err.println("路径不能以/结尾:" + path);
            return false;
        }
        if (path.contains("//")) {
            System.err.println("路径格式不对，存在连续的/:" + path);
            return false;
        }
        if (path.equals("/")) {
            System.err.println("路径格式不对，只有一个/:" + path);
            return false;
        }
        return true;
    }

    /**
     * 获得父级目录
     * @param path /root/abc
     * @return
     */
    private String getParentPath(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(0, index);
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZookManager zoo = new ZookManager("127.0.0.1:2181");
        zoo.setData("/top/enjoy/abc", "abc");
        zoo.setData("/top/enjoy/bbb", "bbb");
        zoo.setData("/top/enjoy/ccc", "ccc");
        System.out.println("成功新增");
        zoo.delete("/top/enjoy");
        System.out.println("成功删除");
    }
}
