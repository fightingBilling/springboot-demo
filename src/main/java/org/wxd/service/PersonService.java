package org.wxd.service;

import org.wxd.domain.Person;

/**
 * Created by wxd on 16-9-14.
 */
public interface PersonService {

    public Person savePersonWithRollback(Person person);

    public Person savePersonWithoutRollback(Person person);

    public Person save(Person person);

    public Person findOne(Person person);

    public void remove(Long id);

    public void setRedisString(String key, String value);

    public String getRedisString(String key);

    public void setRedisPerson(Person person);

    public Person getRedisPerson(String key);
}
