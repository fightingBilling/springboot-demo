package org.wxd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.wxd.domain.Person;

import javax.annotation.Resource;

/**
 * Created by wxd on 16-9-17.
 */
@Repository
public class PersonRedisDao {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> valOpsStr;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valOps;

    public void setString(String key, String value){
        valOpsStr.set(key, value);
    }

    public void save(Person person){
        valOps.set(String.valueOf(person.getId()), person);
    }

    public String getString(String key){
        return valOpsStr.get(key);
    }

    public Person getPerson(String key){
        return (Person)valOps.get(key);
    }
}
