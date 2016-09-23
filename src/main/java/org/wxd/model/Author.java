package org.wxd.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wxd on 16-9-9.
 * 加载properties配置文件中的属性值，默认加载application.properties文件，
 * 如果需要加载其他文件，可以使用@ConfigurationProperties(prefix="",locations={"classpath:config/author.properties"}),参考Author2
 */

@Component
public class Author {
    @Value("${author.name}")
    private String name;

    @Value("${author.age}")
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
