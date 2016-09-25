package org.wxd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxd.dao.PersonCriteriaRepository;
import org.wxd.dao.PersonRepository;
import org.wxd.domain.Person;
import org.wxd.service.PersonService;

import java.util.List;

/**
 * Created by wxd on 16-9-13.
 */
@RestController
public class PersonController {

    //@Autowired
    //private PersonRepository personRepository;

    @Autowired
    private PersonCriteriaRepository personRepository;

    @Autowired
    private PersonService personService;

    @RequestMapping("/save")
    public Person save(String name, String address, int age){
        Person p = new Person();
        p.setName(name);
        p.setAddress(address);
        p.setAge(age);
        personRepository.save(p);
        return p;
    }

    @RequestMapping("/q1")
    public List<Person> findByAddress(String address){
        List<Person> list = personRepository.findByAddress(address);
        return list;
    }

    @RequestMapping("/q2")
    public Person findByNameAndAddres(String name, String address){
        Person person = personRepository.findByNameAndAddress(name, address);
        return person;
    }

    @RequestMapping("/q3")
    public Person withNameAndAddressQuery(String name, String address){
        Person person = personRepository.withNameAndAddressQuery(name,address);
        return person;
    }

    @RequestMapping("/q4")
    public Person withNameAndAddressNamedQuery(String name, String address){
        Person person = personRepository.withNameAndAddressNamedQuery(name, address);
        return person;
    }

    @RequestMapping("/sort")
    public List<Person> sortPerson(String sortBy){
        List<Person> list = personRepository.findAll(new Sort(Sort.Direction.ASC, sortBy));
        return list;
    }

    @RequestMapping("/page")
    public Page<Person> page(int page, int size){
        Page<Person> pagePerson = personRepository.findAll(new PageRequest(page,size, Sort.Direction.ASC, "id"));
        return pagePerson;
    }

    @RequestMapping("/auto")
    public Page<Person> auto(Person person, int page, int size, String sortBy){
        Page<Person> pagePerson = personRepository.findByAuto(person, new PageRequest(page, size, Sort.Direction.DESC, sortBy));

        return pagePerson;
    }

    @RequestMapping("/rollback")
    public Person saveWithRollback(Person person){
        personService.savePersonWithRollback(person);
        return person;
    }

    @RequestMapping("/norollback")
    public Person saveWithoutRollback(Person person){
        personService.savePersonWithoutRollback(person);
        return person;
    }

    @RequestMapping("/cacheput")
    public Person putCache(Person person){
        personService.save(person);
        return person;
    }

    @RequestMapping("able")
    public Person ableCache(Person person){
        Person p = personService.findOne(person);
        return p;
    }

    @RequestMapping("/evit")
    public String evitCache(Long id){
        personService.remove(id);
        return "OK";
    }

    @RequestMapping("/redis/setstr")
    public String setRedisString(String key, String value){
        personService.setRedisString(key, value);
        return "OK";
    }

    @RequestMapping("/redis/getstr")
    public String getRedisString(String key){
        return personService.getRedisString(key);
    }

    @RequestMapping("/redis/setperson")
    public String setRedisPerson(Person person){
        personService.setRedisPerson(person);
        return "OK";
    }

    @RequestMapping("/redis/getperson")
    public Person getRedisPerson(String key){
        return personService.getRedisPerson(key);
    }

}