package org.wxd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wxd.dao.PersonRedisDao;
import org.wxd.dao.PersonRepository;
import org.wxd.domain.Person;
import org.wxd.message.PersonMessage;
import org.wxd.service.PersonService;

/**
 * Created by wxd on 16-9-14.
 * 此类展示声明式事务处理方式：使用transactional注解
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonRedisDao personRedisDao;

    @Autowired
    private PersonMessage personMessage;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Person savePersonWithRollback(Person person) {
        personRepository.save(person);

        if(person.getName().equals("wxd")){
            throw new IllegalArgumentException("wxd is exist, it will be rollback");
        }
        return person;
    }

    @Override
    @Transactional(noRollbackFor = IllegalArgumentException.class)
    public Person savePersonWithoutRollback(Person person) {
        personRepository.save(person);

        if(person.getName().equals("wxd")){
            throw new IllegalArgumentException("wxd is exist, but will not be rollback");
        }
        return person;
    }

    @Override
    @CachePut(value="people", key="#person.id")
    public Person save(Person person) {
        personRepository.save(person);
        System.out.println("为id,key为：" + person.getId() + "数据做了缓存");
        personMessage.sendPersonMessage(person);
        System.out.println("seend person message to rabbit mq, id:" + person.getId());
        return person;
    }

    @Override
    @Cacheable(value="people", key = "#person.id")
    public Person findOne(Person person) {
        Long id = person.getId();
        Person p = personRepository.findOne(id);
        System.out.println("为id,key为" + id + "数据做了缓存");
        return p;
    }

    @Override
    @CacheEvict(value="people")
    public void remove(Long id) {
        System.out.println("删除id,key为:" + id + "的缓存数据");
        personRepository.delete(id);
    }

    @Override
    public void setRedisString(String key, String value) {
        personRedisDao.setString(key,value);
    }

    @Override
    public String getRedisString(String key) {
        return personRedisDao.getString(key);
    }

    @Override
    public void setRedisPerson(Person person) {
        personRedisDao.save(person);
    }

    @Override
    public Person getRedisPerson(String key) {
        return personRedisDao.getPerson(key);
    }
}
