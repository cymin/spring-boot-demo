package com.tsingyun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by chen on 17/5/24.
 */
@RestController
public class TestController {

    private static final String KEY = "HIS_DATA_MODEL";

    @Autowired
    StringRedisTemplate stringRedisTemplate;

   /* @Resource
    RedisTemplate<String, String> redisTemplate;*/

    @Resource(name = "stringRedisTemplate")
    SetOperations<String, String> setOperations;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valueOperations;

    @Resource(name = "stringRedisTemplate")
    HashOperations<String, String, String> hashOperations;

    @GetMapping("/")
    public Object t() {
        String member = "[16385, 192.168.11.254, 2404, 236.2, true, false]";
        Set<String> members = setOperations.members(KEY);
        return members;
    }

    @GetMapping("/t2")
    public Object t2() {
        String member = "[16385, 192.168.11.254, 2404, 236.2, true, false]";
        Boolean isMember = setOperations.isMember(KEY, member);
        return isMember;
    }

    @GetMapping("/t3")
    public Object t3() {
        valueOperations.set("chen", "hello");
        return valueOperations.get("chen");
    }

    @GetMapping("/t4")
    public Object t4() {
        return saveNX("chen", "chen");
    }

    @GetMapping("/t5")
    public Object t5() {
        hashOperations.put("102", "16387", "2222");
        hashOperations.put("102", "16388", "5555");
        stringRedisTemplate.expire("102", 5, TimeUnit.SECONDS);

//        System.out.println(hashOperations.get(102, 16386));
//        System.out.println(hashOperations.get(102, 16387));
//        System.out.println(hashOperations.get(102, 16388));
        System.out.println(hashOperations.get("102", "16389"));
        return hashOperations.values("102");
    }

    /**
     * save if not exist
     *
     * @param key
     * @param val
     * @return
     */
    private boolean saveNX(String key, String val) {
        return stringRedisTemplate.execute((RedisCallback<Boolean>) connection -> connection.setNX(key.getBytes(), val.getBytes()));
    }
}
