package com.xxx.redis;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CacheSimple {
    public CacheSimple() {
        Jedis jedis = new Jedis("10.6.6.117", 19000);
        try {
            jedis.auth("tencent-test-redis");

            List<Goods> goodsList = new ArrayList<>();
            goodsList.add(new Goods(8818, "苹果","", 5.5f));
            goodsList.add(new Goods(8819, "香蕉","", 4.5f));
            goodsList.add(new Goods(8817, "脐橙","", 8.5f));
            for (Goods goods:goodsList) {
                String json = JSON.toJSONString(goods);
                System.out.println(json);
                String key = "goods:" + goods.getGoodsId();
                jedis.set(key, json);
            }

            // System.out.println(jedis.get("goods:8818"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    public static void main(String[] args) {
        new CacheSimple();
        Jedis jedis = new Jedis("10.6.6.117", 19000);
        try {
            jedis.auth("tencent-test-redis");

            String goodsId = new Scanner(System.in).next();
            String key = "goods:" + goodsId;
            if (jedis.exists(key)) {
                String json = jedis.get(key);
                System.out.println(json);
                Goods goods = JSON.parseObject(json, Goods.class);
                System.out.println(goods.getGoodsName());
            } else {
                System.out.println("商品不存在");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }
}
