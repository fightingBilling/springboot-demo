package org.wxd.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by wxd on 16-9-9.
 * 使用@ConfigurationProperties加载配置文件示例：
 */
@Component
@ConfigurationProperties(prefix = "author",locations = {"classpath:config/author2.properties"})
public class Author2 {

    private String name;
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
