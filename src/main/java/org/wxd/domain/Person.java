package org.wxd.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wxd on 16-9-13.
 */
@Entity
@NamedQuery(name = "Person.withNameAndAddressNamedQuery", query="select p from Person p where p.name=?1 and p.address =?2")
public class Person implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME", length = 20)
    private String name;
    @Column (name="AGE")
    private Integer age;
    @Column (name="ADDRESS", length=50)
    private String address;
    @Column (name="SEX", length = 2)
    private String sex;
    @Column (name="EMAIL",unique = true, length = 50)
    private String email;
    @Column (name="MOBILE",unique = true, length = 20)
    private String mobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
