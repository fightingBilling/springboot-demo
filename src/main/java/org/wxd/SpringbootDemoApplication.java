package org.wxd;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxd.jpa.support.JpaCriteriaRepositoryFactoryBean;
import org.wxd.model.Author;
import org.wxd.model.Author2;


@RestController
@SpringBootApplication
// 使用自定义的JpaRepository需要注册自定义的RepositoryFactoryBean
@EnableJpaRepositories(repositoryFactoryBeanClass = JpaCriteriaRepositoryFactoryBean.class)
// 使用cache:不做任何配置的话，默认使用ConcurrentMap作为缓存，不需要添加任何其他依赖包
@EnableCaching
public class SpringbootDemoApplication {

	@Autowired
	private Author author;

	@Autowired
	private Author2 author2;

	@RequestMapping("/hello")
	public String index(){
		return "Hello Spring Boot, Author: " + author.getName() + ", Age: " + author.getAge()
				+ "\nAuthor2: " + author2.getName() + ",Age: " + author2.getAge();
	}

	@Bean
	public Queue getQueue(){
		return new Queue("springbootdemo");
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}
}
