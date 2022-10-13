package com.xxx.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Curator {
    public static void main(String[] args) throws Exception {
        String connStr = "127.0.0.1:2181";
        CuratorFramework cur = CuratorFrameworkFactory.builder()
                .connectString(connStr)
                .connectionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        cur.start();  // 连接

        // 创建监听
        PathChildrenCache cache = new PathChildrenCache(cur, "/zk", true);
        cache.start();
        cache.rebuild();
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework framework, PathChildrenCacheEvent event) throws Exception {
                System.out.println("节点发生变化：" + event.getType());
            }
        });

        String path = "/zk/zhuzhu4";
        Stat stat = cur.checkExists().forPath(path);
        if (stat != null) {
            System.out.println("【"+path+"】节点存在，直接删除");
            cur.delete().forPath(path);
        }
        // cur.delete().forPath(path);
        // System.in.read();

        System.out.println("准备创建【"+path+"】");
        cur.create().withMode(CreateMode.PERSISTENT)
                .forPath(path, "love forever".getBytes());
        System.out.println("节点【"+path+"】创建成功");

        Thread.sleep(1000);

        byte[] bs = cur.getData().forPath(path);
        System.out.println("数据：" + new String(bs));

        Thread.sleep(1000);

        cur.delete().forPath(path);

        Thread.sleep(1000);
    }

    /**
     * 三种watcher来做节点的监听
     *      pathcache   监视一个路径下子节点的创建、删除、节点数据更新
     *      NodeCache   监视一个节点的创建、更新、删除
     *      TreeCache   pathcaceh+nodecache 的合体（监视路径下的创建、更新、删除事件），缓存路径下的所有子节点的数据
     */
    @Test
    public void test() throws Exception {
        String connStr = "127.0.0.1:2181";
        CuratorFramework curatorFramework=CuratorFrameworkFactory.builder()
                .connectString(connStr)
                .connectionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();
        curatorFramework.start();

        /**
         * 节点变化NodeCache
         */
        /*NodeCache cache=new NodeCache(curatorFramework,"/zk",false);
        cache.start(true);
        cache.getListenable().addListener(()-> System.out.println("节点数据发生变化,变化后的结果" +
                "："+new String(cache.getCurrentData().getData())));
        curatorFramework.setData().forPath("/zk","菲菲".getBytes());*/

        /**
         * PatchChildrenCache
         */

        PathChildrenCache cache = new PathChildrenCache(curatorFramework,"/zk",true);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.rebuild();
        // Normal / BUILD_INITIAL_CACHE /POST_INITIALIZED_EVENT

        cache.getListenable().addListener((curatorFramework1,pathChildrenCacheEvent)->{
            switch (pathChildrenCacheEvent.getType()){
                case CHILD_ADDED:
                    System.out.println("增加子节点");
                    break;
                case CHILD_REMOVED:
                    System.out.println("删除子节点");
                    break;
                case CHILD_UPDATED:
                    System.out.println("更新子节点");
                    break;
                default:break;
            }
        });

        String path = "/zk/t1";

        // 创建节点
        /*curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(path, "event".getBytes());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1");*/

        curatorFramework.setData().forPath(path, "222".getBytes());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("3");

        curatorFramework.delete().forPath(path);
        System.out.println("4");
    }
}
