package org.wxd.jpa.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by wxd on 16-9-14.
 * 使用自定义的JpaRepository需要注册自定义的RepositoryFactoryBean,使用以下注解：
 * @EnableJpaRepositories(repositoryFactoryBeanClass = JpaCriteriaRepositoryFactoryBean.class)
 */
@NoRepositoryBean
public interface JpaCriteriaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    public Page<T> findByAuto(T example, Pageable pageable);
}
