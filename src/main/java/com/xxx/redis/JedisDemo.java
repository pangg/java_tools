package com.xxx.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JedisDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("10.6.6.117", 19000);
        try {
            jedis.auth("tencent-test-redis");

            // String
            jedis.set("test_a", "1111");
            System.out.println(jedis.get("test_a"));

            jedis.mset("name1", "value1", "name2", "value2");
            System.out.println(jedis.mget("name1", "name2"));
            jedis.mset(new String[]{"ttt1", "vvv1", "ttt2", "vvv2"});
            List<String> list = jedis.mget(new String[]{"ttt1", "ttt2"});
            System.out.println(list);

            jedis.set("tage", "10");
            System.out.println(jedis.incr("tage"));

            // Hash
            String hkey = "student:12";
            jedis.hset(hkey, "name", "小米");
            System.out.println(jedis.hget(hkey, "name"));

            Map<String, String> stuMap = new HashMap<>();
            stuMap.put("age", "16");
            stuMap.put("id", "100");
            jedis.hmset(hkey, stuMap);
            System.out.println(jedis.hmget(hkey, new String[]{"name", "id"}));
            System.out.println(jedis.hgetAll(hkey));

            // List
            String lKey = "letter";
            jedis.del(lKey);
            jedis.rpush(lKey, new String[]{"d", "e", "f"});
            jedis.lpush(lKey, new String[]{"c", "b", "a"});
            List<String> letter = jedis.lrange(lKey, 0, -1);
            System.out.println(letter);
            jedis.rpop(lKey);
            jedis.lpop(lKey);
            letter = jedis.lrange(lKey, 0, -1);
            System.out.println(letter);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

    }
}
