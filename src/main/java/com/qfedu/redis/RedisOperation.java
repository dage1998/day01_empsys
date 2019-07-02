package com.qfedu.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/***
 *  佛曰:
 *          写字楼里写字间，写字间里程序员；
 *          程序人员写程序，又拿程序换酒钱。
 *          酒醒只在网上坐，酒醉还来网下眠；
 *          酒醉酒醒日复日，网上网下年复年。
 *          但愿老死电脑间，不愿鞠躬老板前；
 *          奔驰宝马贵者趣，公交自行程序员。
 *          别人笑我忒疯癫，我笑自己命太贱；
 *          不见满街漂亮妹，哪个归得程序员？
 */

@Component
public class RedisOperation {


    @Autowired
    private JedisPool jedisPool;


    //向redis中的哈希结构中塞值
    public  void   setHash(String key ,String field,String value){


        Jedis jedis = jedisPool.getResource();

        jedis.hset(key, field, value);

        jedis.close();


    }


    //从redis中的哈希结构中取值
    public String getHash(String key ,String field){

        Jedis jedis = jedisPool.getResource();
        String s = jedis.hget(key, field);

        jedis.close();

        return  s;

    }
}
